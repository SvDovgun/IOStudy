package com.luxoft.iostudy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.luxoft.iostudy.FileAnalyzer.*;
import static org.junit.jupiter.api.Assertions.*;

public class FileAnalyzerTest {
    public static final String file = "src/test/resource/test.txt";
    public static final String textWithSearchWord = "In this chapter, we will cover the Java Application Programming Interfaces (APIs) for input and output! You will learn how to access files and directories and how to read and write data in binary and text format. This chapter also shows you the object serialization mechanism that lets you store objects as easily as you can store text or numeric data. Sentences with Input for search upper case! Next, we will turn to working with files and directories. We finish the chapter with a discussion of regular expressions, even though they are not actually related to input and output. We couldn’t find a better place to handle that topic, and apparently neither could the Java team—the regular expression API specification was attached to a specification request for new I/O features.\n" +
            "In the Java API, an object from which we can read a sequence of bytes is called an input stream. An object to which we can write a sequence of bytes is called an output stream. The abstract classes InputStream and OutputStream form the basis for a hierarchy of input/output (I/O) classes.";
    public static final String textWithoutWord = "In this chapter, we will cover the Java Application Programming Interfaces (APIs). You will learn how to access files and directories and how to read and write data in binary and text format. We finish the chapter with a discussion of regular expressions, We couldn’t find a better place to handle that topic, and apparently neither could the Java team—the regular expression API specification was attached to a specification request for new I/O features.\n" +
            "In the Java API, an object from which we can read a sequence of bytes is called an WWW stream. An object to which we can write a sequence of bytes is called an output stream. ";
    public static final String searchWord = "input";



    @Test
    @DisplayName("Test if text in file contain searched word expected times")
    void testContentSearchedInFile() throws IOException {
        writeContent(file, textWithSearchWord);
        assertEquals(5, countInFile(file, searchWord) );

    }

    @Test
    @DisplayName("Test searched word in empty file")
    void testSearchedInEmptyFile() throws IOException {
        writeContent(file, "");
        assertEquals(0, countInFile(file, searchWord) );

    }

    @Test
    @DisplayName("Test if textWithoutWord doesn't contain searched word")
    void testNotContentSearchedInFile() throws IOException {
        writeContent(file, textWithoutWord);
        assertEquals(0, countInFile(file, searchWord) );
    }


    @Test
    @DisplayName("Test count of sentences in file contain searched word expected times")
    void testSentencesWithSearchedInFile() throws IOException {
        writeContent(file, textWithSearchWord);
        assertEquals(5, countSentencesWith(file, searchWord).length );

    }
}
