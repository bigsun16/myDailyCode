package com.qihui.sun.nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel {
    public static void main(String[] args) throws IOException {
        File file = new File("hello.txt");
//        writeData2File(file);
//        readDataFromFile(file);
//        copyFile();
        copyFile2();
    }

    private static void copyFile2() throws IOException {
        FileInputStream inputStream = new FileInputStream("hello.txt");
        FileChannel sourceChannel = inputStream.getChannel();
        FileOutputStream outputStream = new FileOutputStream("hello_copy.txt");
        FileChannel descChannel = outputStream.getChannel();
        descChannel.transferFrom(sourceChannel,0,sourceChannel.size());
        inputStream.close();
        outputStream.close();
    }

    private static void copyFile() throws IOException {
        FileInputStream inputStream = new FileInputStream("hello.txt");
        FileChannel readChannel = inputStream.getChannel();
        FileOutputStream outputStream = new FileOutputStream("hello_copy.txt");
        FileChannel writeChannel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        while (true){
            buffer.clear();//如果没有这个复位，position和limit数值相同，将无法继续读入，那么读入的结果将会一直是0
            int read = readChannel.read(buffer);
            if (read==-1){
                break;
            }
            buffer.flip();
            writeChannel.write(buffer);
        }
        inputStream.close();
        outputStream.close();
    }

    private static void readDataFromFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int)file.length());
        channel.read(buffer);
        System.out.println(new String(buffer.array()));
        inputStream.close();
    }

    private static void writeData2File(File file) throws IOException {
        String str = "hello world";
        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        buffer.flip();
        fileChannel.write(buffer);
        fileOutputStream.close();
    }
}
