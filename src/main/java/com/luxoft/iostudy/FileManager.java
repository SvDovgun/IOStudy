package com.luxoft.iostudy;

import java.io.*;


public class FileManager {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) throws IOException {
        String path;
        try {
            path = args[0];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            path = "H:\\Films\\YouTube\\Eurovision";
        }

        System.out.println("Review " + path  );
        System.out.println("Files in " + path + " is " + ANSI_BLUE +  countFiles(path) + ANSI_RESET);
        System.out.println("Folders in " + path + " is " + ANSI_BLUE +  countDirs("" + path) + ANSI_RESET);

        String copyFrom ="H:\\Films\\YouTube\\Life.mp4";
        String copyTo = "H:\\Films\\Study";
        System.out.println("Copying all existed file(s) from " +  ANSI_GREEN + copyFrom + ANSI_RESET + " to " +  ANSI_GREEN + copyTo + ANSI_RESET);
        copy( copyFrom,   copyTo);
        String moveFrom = "H:\\Films\\YouTube\\Eurovision";
        String moveTo= "E:\\Videos\\Eurovision";
        System.out.println("Moving (with delete in source) all existed file(s) from " +  ANSI_GREEN + moveFrom + ANSI_RESET + " to " +  ANSI_GREEN + moveTo + ANSI_RESET);
          //      copy("H:\\Films\\YouTube\\Eurovision", "E:\\Videos\\Eurovision");
        move(moveFrom, moveTo);

    }

    private static File[] listFiles(String path) {
        File pathDir = new File(path);
        File[] files = pathDir.listFiles();
        return files;
    }

    // public static int countFiles(String path) - принимает путь к папке,
    // возвращает количество файлов в папке и всех подпапках по пути
    public static int countFiles(String path) {
        int counter = 0;
        for (File object : listFiles(path)) {
            if (!object.isDirectory() ){
                counter++;
            } else {
                counter = counter + countFiles(object.getPath());
            }
        }
        return counter;
    }

    // public static int countDirs(String path) - принимает путь к папке,
    // возвращает количество папок в папке и всех подпапках по пути
    public static int countDirs(String path) {
        int counter = 0;
        for (File object : listFiles(path)) {
            if (object.isDirectory()){
                counter++;
                counter = counter + countDirs(object.getPath());
            }
        }
        return counter;
    }

    public static void copy(String from, String to) throws IOException { //- метод по копированию папок и файлов.
        // Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
        File fileFrom = new File(from);
        File fileTo = new File(to);

        if (fileFrom.isDirectory()) {
            copyDir(fileFrom, fileTo);
        } else if (fileFrom.isFile()) {
            copyFile(fileFrom, fileTo);
        }

    }

    private static void copyDir(File from, File to) throws IOException {
        if (!to.exists()) {
            to.mkdir();
        }
        to = new File(to.getPath());
        for (File file : from.listFiles()) {
            if (file.isFile()){
                copyFile(file, new File(to.getPath() , file.getName() ));
            } else if (file.isDirectory()){
                copyDir(file, new File(to.getPath() , file.getName()));
            }

        }
    }

    private static void copyFile(File from, File to) throws IOException {
        if (to.isDirectory()) {
            to = new File(to.getPath() ,  from.getName());
        }
        if (!to.exists()) {
            to.createNewFile();
        }
        //read all byte from file

        InputStream inputStream = new FileInputStream(from);
        byte[] bytes = inputStream.readAllBytes();
        //write all bytes to
        OutputStream outputStream = new FileOutputStream(to.getPath());
        outputStream.write(bytes);
        inputStream.close();
        outputStream.close();
    }

    public static void move(String from, String to) throws IOException { // - метод по перемещению папок и файлов.
        //Параметр from -путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
        new File(from).renameTo(new File(to));

    }

    private static void deleteFileDIR(File fileFrom) {
        for (File file : fileFrom.listFiles()) {
            if (file.isDirectory()) {
                deleteFileDIR(file);
                System.out.println(ANSI_RED + "Deleting file " + file + ANSI_RESET);
                file.delete();
                System.out.println(ANSI_GREEN + file + " is deleted" + ANSI_RESET);
            }
            System.out.println(ANSI_RED + "Deleting file " + file + ANSI_RESET);
            file.delete();
            System.out.println(ANSI_GREEN + file + " is deleted" + ANSI_RESET);
        }

    }
}
