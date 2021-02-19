package com.qihui.sun.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(9999);
        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long len = socketChannel.read(byteBuffers);
                byteRead += len;
                System.out.println("byteRead=" + byteRead);
                Arrays.asList(byteBuffers).stream()
                        .map(byteBuffer -> "position=" + byteBuffer.position() + ", limit=" + byteBuffer.limit())
                        .forEach(System.out::println);
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
            int byteWrite = 0;
            while (byteWrite < messageLength) {
                long writeLen = socketChannel.write(byteBuffers);
                byteWrite += writeLen;
                System.out.println("byteRead:=" + byteRead + " byteWrite:=" + byteWrite + " messageLength:=" + messageLength);
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());
        }
    }
}
