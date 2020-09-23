package com.qihui.sun.io;

import java.io.*;

public class TestStream {
    public static void main(String[] args) throws IOException {
//        testFileOutputStream();
//        testFileInputStream();
//        testFileWriter();
//        testFileReader();
//        testBufferedWriter();
//        testBufferedReader();
//        testOutPutStreamWriter();
//        testInputStreamReader();
        testCopyFile();
    }

    private static void testCopyFile() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("d:\\02_L001.mp3"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("d:\\copy.mp3"));
        byte[] array = new byte[1024];
        int len = 0;
        while ((len = bufferedInputStream.read(array))!=-1){
            bufferedOutputStream.write(array,0,len);
            bufferedOutputStream.flush();
        }
        bufferedOutputStream.close();
        bufferedInputStream.close();
    }

    private static void testInputStreamReader() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream("testOutputStreamWriter.txt")),"utf-8"));
        String line = "";
        while ((line = br.readLine())!=null){
            System.out.println(line);
        }
    }

    private static void testOutPutStreamWriter() throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("testOutputStreamWriter.txt"),"utf-8");
        osw.write("锄禾日当午，");
        osw.write("\r\n");
        osw.write("汗滴禾下土；");
        osw.write("\r\n");
        osw.write("谁知盘中餐，");
        osw.write("\r\n");
        osw.write("粒粒皆辛苦。");
        osw.close();

    }


    private static void testBufferedReader() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("d:\\testBufferedWriter.txt"));
        String line = "";
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
    }

    private static void testBufferedWriter() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("d:\\testBufferedWriter.txt"));
        bw.write("你好吗？");
        bw.newLine();
        bw.write("I am fine!");
        bw.close();
    }

    private static void testFileReader() throws IOException {
        FileReader fr = new FileReader("d:\\testFileWriter.txt");
        int len = 0;
        char[] array = new char[1024];
        while ((len=fr.read(array))!=-1){
            System.out.println(new String(array,0,len));
        }
        fr.close();
    }

    private static void testFileWriter() throws IOException {
        FileWriter fw = new FileWriter("d:\\testFileWriter.txt");
        fw.write("我的名字是孙齐辉bigsun");
        fw.close();
    }


    private static void testFileInputStream() throws IOException {
        FileInputStream fis = new FileInputStream("d:\\testOutputStream.txt");
        int len = 0;
        byte[] array = new byte[1024];
        while ((len=fis.read(array))!=-1){
            System.out.println(new String(array,0,len));
        }

    }

    private static void testFileOutputStream() throws IOException {
        FileOutputStream fos = new FileOutputStream("d:\\testOutputStream.txt");
        fos.write("hello world".getBytes());
        fos.write("\r\n".getBytes());
        fos.write("我是中国人".getBytes());
        fos.close();
    }
}
