package com.luxoft.iostudy;

import java.io.File;
import java.io.IOException;

public class FileManager {
    public static void main(String[] args) throws IOException {
        File path = new File("C:\\Windows");
        System.out.println(path.isDirectory());
        File[] innerPaths = path.listFiles();
        for (File innerPath : innerPaths) {
            String type = innerPath.isFile() ? "FILE: " : "DIR: ";
            System.out.println(type + innerPath);
        }

    }
}
