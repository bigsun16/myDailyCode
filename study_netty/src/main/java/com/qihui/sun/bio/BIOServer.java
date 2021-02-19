package com.qihui.sun.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//进入cmd敲击"telnet 127.0.0.1 'port'"链接服务，然后alt+[,然后输入"send 字符串"来测试
public class BIOServer {
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器启动了");
        while (true){
            final Socket socket = serverSocket.accept();
            System.out.println("链接到一个客户端==>"+Thread.currentThread().getName());
            executorService.execute(()->{
                handler(socket);
            });
        }
    }

    private static void handler(Socket socket) {
        try{
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true){
                int read = inputStream.read(bytes);
                if (read!=-1){
                    System.out.println(new String(bytes,0,read)+"==>"+Thread.currentThread().getName());
                } else {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                System.out.println("关闭client链接==>"+Thread.currentThread().getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
