package com.qihui.sun.nio.zeroCopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

//零拷贝是指没有cpu拷贝，但是有DMA拷贝，直接内存拷贝
public class NioCopyServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress(9999);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(address);
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCount = 0;
            while (-1 != readCount) {
                try {
                    readCount = socketChannel.read(buffer);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                buffer.rewind();//倒带，position=0，mark作废
            }
        }
    }
}
