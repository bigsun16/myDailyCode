package com.qihui.sun.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class TransferFileUtil {

	private void closeStream(InputStream stream) {
		if (stream != null) {
			try {
				System.out.println("stream........." + stream);
				stream.close();
				System.out.println("stream........." + stream);
			} catch (Exception e) {
				System.out.println("e........." + e);
			}
		}
	}

	private void createDirectoryIfNotExist(FTPClient client, String remotePath) throws Exception {
		String path = remotePath;
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		String dirs[] = path.split(File.separator);
		boolean dirExist = true;
		for (String dir : dirs) {

			if (dirExist) {
				try {
					dirExist = client.changeWorkingDirectory(dir);
				} catch (IOException e) {
					throw new Exception(e.getMessage());
				}
			}
			if (!dirExist) {
				try {
					if (!client.makeDirectory(dir)) {
						throw new Exception("Unable to create remote directory '" + client.printWorkingDirectory()
								+ File.separator + dir + "'.  error='" + client.getReplyString() + "'");
					}
					if (!client.changeWorkingDirectory(dir)) {
						throw new Exception("Unable to change into newly created remote directory '"
								+ client.printWorkingDirectory() + File.separator + dir + "'.  error='"
								+ client.getReplyString() + "'");
					}
				} catch (Exception e) {
					throw new Exception(e.getMessage());
				}
			}

		}
	}

	private static Set<String> m_binaryFileType = new HashSet<String>();

	static {
		m_binaryFileType.add(".gz");
	}

	public boolean isUseFTPBinaryFileType(String fileName) {
		for (String s : m_binaryFileType) {
			if (fileName.endsWith(s)) {
				return true;
			}
		}
		return false;
	}

	public void doFTPTransfer(List<File> localFiles, String remoteDir, String ipAddress, String username,
			String password) throws Exception {
		FTPClient client = new FTPClient();
		InetAddress address;
		FileInputStream is = null;
		try {
			address = InetAddress.getByName(ipAddress);
			client.connect(address);
			if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
				client.disconnect();
				System.out.println(
						"FTP server refused connection, FTP server address: " + ipAddress + "username: " + username);
				throw new Exception("FTP server refused connection");
			}
			if (client.login(username, password)) {
				// Begin transferring
				System.out.println(
						"Starting FTP transfer..., FTP server address: " + ipAddress + "username: " + username);
				System.out.println("remoteDir............" + remoteDir);
				createDirectoryIfNotExist(client, remoteDir);
				for (File file : localFiles) {
					// Close stream before new
					closeStream(is);
					is = new FileInputStream(file);
					String filename = file.getName();
					if (isUseFTPBinaryFileType(filename)) {
						System.out.println("File transfer in BINARY file type");
						client.setFileType(FTP.BINARY_FILE_TYPE);
					} else {
						System.out.println("File transfer in ASCII file type");
						client.setFileType(FTP.ASCII_FILE_TYPE);
					}
					boolean isSuccess = client.storeFile(filename, is);
					if (!isSuccess) {
						throw new Exception("File transfer failed. " + client.getReplyString());
					}
				}
				client.logout();
				System.out.println("FTP transfer completed");
			} else {
				throw new Exception("Unable to login to FTP server. Unknown username or password");
			}
		} catch (UnknownHostException e) {
			throw new Exception("UnknownHostException. FTP server address: " + ipAddress);
		} catch (SocketException e) {
			throw new Exception(e.getMessage());
		} catch (IOException e) {
			throw new Exception("Exception in file transfer session. " + e.getMessage());
		} finally {
			closeStream(is);
			if (client.isConnected()) {
				try {
					System.out.println("client.isConnected()............." + client.isConnected());
					client.disconnect();
					System.out.println("client.isConnected()............." + client.isConnected());
				} catch (IOException e) {
					System.out.println("e11111111" + e);
				}
			}

		}
	}

	private void createDirectoryIfNotExist(ChannelSftp channel, String remotePath) throws Exception, IOException {
		String path = remotePath;
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		String dirs[] = path.split(File.separator);
		StringBuffer completeDir = new StringBuffer();
		for (int i = 0; i < dirs.length; i++) {
			completeDir.append(File.separator);
			completeDir.append(dirs[i]);
			try {
				channel.lstat(completeDir.toString());
			} catch (SftpException error) {
				if (error.getMessage().contains("No such file")) {
					try {
						channel.mkdir(completeDir.toString());
						channel.chmod(0777, remotePath);
					} catch (SftpException e) {
						throw new IOException("Error during create directory " + e.getMessage());
					}
				} else {
					throw new IOException("Error during create directory " + error.getMessage());
				}
			}
		}
	}

	private static JSch jsch = null;
	private static Session session = null;

	public static void connect(String ipAddress, String username, String password) throws JSchException {
		jsch = new JSch();
		session = jsch.getSession(username, ipAddress, 22);
		session.setPassword(password);
		Properties conf = new Properties();
		conf.put("StrictHostKeyChecking", "no");
		session.setConfig(conf);
		session.connect();
	}

	/**
	 * 执行相关的命令     
	 * 
	 * @throws JSchException
	 *                 
	 */
	public static void execCmd(String command, String ipAddress, String username, String password)
			throws JSchException {
		connect(ipAddress, username, password);
		BufferedReader reader = null;
		Channel channel = null;
		try {
			while (command != null) {
				channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand(command);

				channel.setInputStream(null);
				((ChannelExec) channel).setErrStream(System.err);

				channel.connect();
				InputStream in = channel.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				String buf = null;
				while ((buf = reader.readLine()) != null) {
					System.out.println(buf);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			channel.disconnect();
			session.disconnect();
		}
	}

	private static final int DEFAULT_SERVER_ALIVE_INTERVAL = Integer
			.valueOf(System.getProperty("ssh.server.aliveinterval", "50000"));
	private static int SESSION_TIMEOUT = 30 * 1000;

	public static Session getSession(String userName, String hostName, String password) {
		// JSch jsch = new JSch();
		Session session = null;
		try {
			session = getSession(userName, hostName, password);
			// session = jsch.getSession(userName, hostName);
			// session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setServerAliveInterval(DEFAULT_SERVER_ALIVE_INTERVAL);
			session.connect(SESSION_TIMEOUT);
		} catch (JSchException e) {
		}
		return session;
	}

	public static ChannelSftp getSftpClient(Session serverSession) throws IOException {
		ChannelSftp channelsftp;
		try {
			channelsftp = (ChannelSftp) serverSession.openChannel("sftp");
			channelsftp.connect();
		} catch (JSchException e) {
			throw new IOException(e);
		}
		return channelsftp;
	}

	public void doSFTPTransfer(List<File> localFiles, String remoteDir, String ipAddress, String username,
			String password) throws Exception {
		FileInputStream fStream = null;
		ChannelSftp channel = null;
		connect(ipAddress, username, password);
		try {

			if (session != null && session.isConnected()) {
				channel = getSftpClient(session);
				createDirectoryIfNotExist(channel, remoteDir);
				System.out.println("Starting a secure file transfer.......");
				for (File file : localFiles) {
					// Close stream before new
					closeStream(fStream);
					fStream = new FileInputStream(file);
					String realFilePath = remoteDir.concat(File.separator)
							.concat(FilenameUtils.getName(file.getAbsolutePath()));
					System.out.println("sftp  realFilePath----------------" + realFilePath);
					channel.put(fStream, realFilePath);
				}

				System.out.println("SFTP transfer completed");

			} else {
				throw new Exception("Cannot open SFTP Connection.");
			}
		} catch (SftpException | IOException e) {
			throw new Exception(e.getMessage());
		} finally {
			closeStream(fStream);
			close(session, channel);
		}
	}

	public static void close(Session session, Channel channel) {
		System.out.println("Closing sftp connection");
		close(channel);
		close(session);
	}

	public static void close(Session session) {
		try {
			if (session != null) {
				session.disconnect();
			}
		} catch (Exception ignore) {
		}
	}

	public static void close(Channel channel) {
		if (channel != null) {
			try {
				channel.disconnect();
			} catch (Exception ignore) {
			}
		}
	}
}
