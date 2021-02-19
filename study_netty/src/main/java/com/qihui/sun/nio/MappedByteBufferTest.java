package com.qihui.sun.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

//可以让文件之间在内存（堆外内存）修改，操作系统不需要拷贝一份
public class MappedByteBufferTest {
    public static void main(String[] args)  throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("hello.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0,(byte)'H');
        mappedByteBuffer.put(3,(byte)'9');
//        mappedByteBuffer.put(5,(byte)'9');//IndexOutOfBoundsException只能读取5个元素，index应该是4
        randomAccessFile.close();
    }
}
