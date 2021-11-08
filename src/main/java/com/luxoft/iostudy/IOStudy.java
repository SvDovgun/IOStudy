package com.luxoft.iostudy;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IOStudy {
    public static void main(String[] args) throws IOException {
//        File path = new File("test.log");
//        System.out.println(path.exists());
//        path.createNewFile();
//        System.out.println(path.exists());
//        System.out.println(path.getAbsolutePath());

        // InputStream <-> OutputStream

        writeContent("test2.log", "Hello JAVA!");
        String content = readContent("test2.log");
        System.out.println(content);

    }

    static void writeContent(String path, String content) throws IOException {
        // write -> 1 byte
        // void write(int value)
        // void write(byte[] buffer)
        // new FileOutputStream(path).write(content.getBytes());
        OutputStream outputStream = new FileOutputStream(path);
        byte[] bytes = content.getBytes();
        outputStream.write(bytes);
    }

    static String readContent(String path) throws IOException {
        File pathToFile = new File(path);
        InputStream inputStream = new FileInputStream(pathToFile);
        int fileLength = (int) pathToFile.length();

        byte[] contentArray = new byte[fileLength];

        inputStream.read(contentArray);
//        int index = 0;
//        int value;
//        while(true) {
//            value = inputStream.read();
//            if (value == -1) {
//                break;
//            }
//            contentArray[index] = (byte) value;
//            index++;
//        }

        return new String(contentArray);
    }


}
