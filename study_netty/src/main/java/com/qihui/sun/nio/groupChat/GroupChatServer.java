package com.qihui.sun.nio.groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 9999;

    public GroupChatServer() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
//                int count = selector.select(2000);
                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            SocketChannel channel = listenChannel.accept();
                            channel.configureBlocking(false);
                            channel.register(selector, SelectionKey.OP_READ);
                            System.out.println(channel.getRemoteAddress() + " 上线了 ");
                        }
                        if (key.isReadable()) {
                            readMessage(key);
                        }
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待。。。");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage(SelectionKey selectionKey) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) selectionKey.channel();
            ByteBuffer buff = ByteBuffer.allocate(1024);
            int count = channel.read(buff);
            if (count > 0) {
                String message = new String(buff.array());
                System.out.println("from client :" + message);
                sendInfo2OtherClients(message, channel);
            }

        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了。。。");
                selectionKey.cancel();
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
//            e.printStackTrace();
        }
    }

    private void sendInfo2OtherClients(String message, SocketChannel selfChannel) throws IOException {
        System.out.println("服务器转发消息中。。。");
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != selfChannel) {
                SocketChannel descChannel = (SocketChannel) targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
                descChannel.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
