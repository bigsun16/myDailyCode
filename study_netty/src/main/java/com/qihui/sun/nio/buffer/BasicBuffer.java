package com.qihui.sun.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
//        intBuffer();
        readOnlyBuffer();
    }

    private static void readOnlyBuffer() {
        IntBuffer buffer = IntBuffer.allocate(64);
        for (int i = 0; i < 64; i++) {
            buffer.put(i);
        }
        buffer.flip();//进行读写转换
        IntBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
        readOnlyBuffer.put(2);
    }

    private static void intBuffer() {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity()-1; i++) {
            intBuffer.put(i * 2);
        }
        intBuffer.flip();//进行读写转换
//        intBuffer.position(1);//设置读取起始位
//        intBuffer.limit(intBuffer.capacity()-2);//设置最大读取量
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }

    public static void ByteBufferPutAndRead(){
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.putInt(4);
        buffer.putLong(5);
        buffer.putChar('中');
        buffer.putShort((short)6);
        buffer.flip();
        //读取是按照写入的数据类型顺序
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }
}
