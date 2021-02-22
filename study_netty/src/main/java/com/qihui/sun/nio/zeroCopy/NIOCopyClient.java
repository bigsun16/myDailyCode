package com.qihui.sun.nio.zeroCopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NIOCopyClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 9999));
        String fileName = "***.zip";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();
        //在Linux下一个transferTo方法就可以完成传输
        //在Windows下 一次调用transferTo只能发送8M,就需要分段传输文件，而且要注意起点传输时的位置
        //transferTo底层用的零拷贝
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送总字节数：" + transferCount + "耗时：" + (System.currentTimeMillis() - startTime));
        fileChannel.close();

    }
}
