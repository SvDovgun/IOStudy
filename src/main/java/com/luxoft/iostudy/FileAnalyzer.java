package com.luxoft.iostudy;

import java.io.*;

public class FileAnalyzer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_LIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_UNBOLD = "\u001B[21m";
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String ANSI_STOP_UNDERLINE = "\u001B[24m";
    public static final String ANSI_BLINK = "\u001B[5m";

    public static void main(String[] args) throws IOException {
        String file, searchWord;
        try {
            file = args[0];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            file = "test2.log";
        }
        try {
            searchWord = args[1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            searchWord = "input";
        }

        String textToFile ="In this chapter, we will cover the Java Application Programming Interfaces (APIs) for input and output! You will learn how to access files and directories and how to read and write data in binary and text format. This chapter also shows you the object serialization mechanism that lets you store objects as easily as you can store text or numeric data. Sentences with Input for search upper case! Next, we will turn to working with files and directories. We finish the chapter with a discussion of regular expressions, even though they are not actually related to input and output. We couldn’t find a better place to handle that topic, and apparently neither could the Java team—the regular expression API specification was attached to a specification request for new I/O features.\n" +
                "In the Java API, an object from which we can read a sequence of bytes is called an input stream. An object to which we can write a sequence of bytes is called an output stream. The abstract classes InputStream and OutputStream form the basis for a hierarchy of input/output (I/O) classes.";
        writeContent(file, textToFile);
        String text = readContent(file);
        System.out.println("File " + ANSI_BLUE +  file + ANSI_RESET +  " contains the searched word " + ANSI_PURPLE + searchWord + ANSI_RESET + " - " + countOfContain(text,searchWord) + " times");
        System.out.println("Next " + ANSI_GREEN + "sentences "+ ANSI_RESET + "contain word " + ANSI_PURPLE + searchWord + ANSI_RESET + " : " );
        print(searchSentenceWith(text, searchWord));

    }

    static void writeContent(String path, String content) throws IOException {
        OutputStream outputStream = new FileOutputStream(path);
        byte[] bytes = content.getBytes();
        outputStream.write(bytes);
        outputStream.close();
    }

    static String readContent(String path) throws IOException {
        File pathToFile = new File(path);
        FileInputStream inputFileStream = new FileInputStream(pathToFile);

        byte[] contentFile = inputFileStream.readAllBytes();

        inputFileStream.close();

        return new String(contentFile);
    }

    static int countOfContain(String text, String searched)  {
        int counter = 0;
        String[] words = text.toLowerCase().replaceAll("[(—.,!?’/)]", " ").split("\\s");
        for (String word : words) {
            if (word.equals(searched))
                counter++;
        }
        return  counter;
    }

    static String[] searchSentenceWith(String text, String searched) {
        int arrayLength = countOfContain(text, searched);
        int index = 0;
        String[] sentences = text.split("[!.?\n]");

        String[] sentenceWith = new String[arrayLength];
        for (String sentence : sentences) {
            if (countOfContain(sentence, searched) > 0) {
                sentenceWith[index] = sentence ;
                index++;
            }
        }

        return sentenceWith;
    }

    static void print(String[] array){
        for (String s : array) {
            System.out.println(s);
        }
    }
}
