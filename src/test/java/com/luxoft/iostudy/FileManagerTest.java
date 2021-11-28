package com.luxoft.iostudy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.luxoft.iostudy.FileManager.*;
import static org.junit.jupiter.api.Assertions.*;


public class FileManagerTest {
    static String pathToFileForCopy = "src/test/resource/test.txt";
    static String testDir = "src/test/resource";
    static String dir1 = "src/test/resource/Dir1";
    static String dir2 = "src/test/resource/Dir2";
    static String dir2In1 = "src/test/resource/Dir1/Dir2";
    static String dir3 = "src/test/resource/Dir2/Dir3";

    @BeforeEach
    void preCondition() throws IOException {
        File dir1 = new File("src/test/resource/Dir1");
        dir1.mkdir();
        File file1 = new File("src/test/resource/Dir1/file1.txt");
        file1.createNewFile();

        File dir2 = new File("src/test/resource/Dir2");
        dir2.mkdir();
        File file5 = new File("src/test/resource/Dir2/file5.txt");
        file5.createNewFile();
        File dir3 = new File("src/test/resource/Dir2/Dir3"); //empty dir
        dir3.mkdir();
        File dir4 = new File("src/test/resource/Dir2/Dir4");
        dir4.mkdir();
        File file2 = new File("src/test/resource/Dir2/Dir4/file2.txt");
        file2.createNewFile();
        File file2_1 = new File("src/test/resource/Dir2/Dir4/file2_1.txt");
        file2_1.createNewFile();
        File file3 = new File("src/test/resource/Dir2/Dir4/file3.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file3);
        fileOutputStream.write("It is not empty file - file3.txt".getBytes());
        fileOutputStream.close();
        File dir5 = new File("src/test/resource/Dir2/Dir5");
        dir5.mkdir();
        File file4 = new File("src/test/resource/Dir2/Dir5/file4.txt");
        file4.createNewFile();
    }

    @AfterEach
    void postCondition() throws InterruptedException {
        File file4 = new File("src/test/resource/Dir2/Dir5/file4.txt");
        file4.delete();
        File dir5 = new File("src/test/resource/Dir2/Dir5");
        dir5.delete();
        File file2 = new File("src/test/resource/Dir2/Dir4/file2.txt");
        file2.delete();
        File file2_f1 = new File("src/test/resource/Dir2/Dir4/file2_1.txt");
        file2_f1.delete();
        File file33 = new File("src/test/resource/Dir2/Dir4/file3.txt");
        file33.delete();
        File dir4 = new File("src/test/resource/Dir2/Dir4");
        dir4.delete();
        File dir3 = new File("src/test/resource/Dir2/Dir3");
        dir3.delete();
        File file5 = new File("src/test/resource/Dir2/file5.txt");
        file5.delete();
        File dir2 = new File("src/test/resource/Dir2");
        dir2.delete();


        File file4_1 = new File("src/test/resource/Dir1/Dir2/Dir5/file4.txt");
        file4_1.delete();
        File dir5_1 = new File("src/test/resource/Dir1/Dir2/Dir5");
        dir5_1.delete();
        File file3_1 = new File("src/test/resource/Dir1/Dir2/Dir4/file3.txt");
        file3_1.delete();
        File file2_1 = new File("src/test/resource/Dir1/Dir2/Dir4/file2.txt");
        file2_1.delete();
        File file2_f1_1 = new File("src/test/resource/Dir1/Dir2/Dir4/file2_1.txt");
        file2_f1_1.delete();
        File dir4_1 = new File("src/test/resource/Dir1/Dir2/Dir4");
        dir4_1.delete();
        File dir3_1 = new File("src/test/resource/Dir1/Dir2/Dir3");
        dir3_1.delete();
        File file5_1 = new File("src/test/resource/Dir1/Dir2/file5.txt");
        file5_1.delete();
        File dir2_1 = new File("src/test/resource/Dir1/Dir2");
        dir2_1.delete();
        File file1 = new File("src/test/resource/Dir1/file1.txt");
        file1.delete();
        File file = new File ("src/test/resource/Dir1/test.txt");
        file.delete();
        File dir1 = new File("src/test/resource/Dir1");
        dir1.delete();

    }


    @Test
    @DisplayName("Test count of Files in test resource folder")
    void testCountOfFiles()  {
        assertEquals(7, countFiles(testDir));
    }

    @Test
    @DisplayName("Test count of Files in empty folder")
    void testCountOfFilesInEmptyDir()  {
        assertEquals(0, countFiles(dir3));
    }

    @Test
    @DisplayName("Test count of Folders/Directories in test resource folder")
    void testCountOfFolders()  {
        assertEquals(5, countDirs(testDir));
    }

    @Test
    @DisplayName("Test count of Files after copy ")
    void testCountOfFilesAfterCopy() throws IOException {
        assertEquals(1, countFiles(dir1));

        copy( pathToFileForCopy,  dir1);

        assertEquals(2, countFiles(dir1));
    }

    @Test
    @DisplayName("Test count of Files/Directory after COPY directory")
    void testCountOfFilesAfterCopyDirectory() throws IOException {
        assertEquals(1, countFiles(dir1));
        assertEquals(0, countDirs(dir1));

        copy( dir2,  dir2In1);

        assertEquals(6, countFiles(dir1));
        assertEquals(4, countDirs(dir1));
        assertEquals(5, countFiles(dir2));
        assertEquals(3, countDirs(dir2));
        assertEquals(12, countFiles(testDir));
        assertEquals(9, countDirs(testDir));

    }

    @Test
    @DisplayName("Test count of Files/Directory after MOVE directory")
    void testCountOfFilesAfterMoveDirectory() throws IOException {
        assertEquals(1, countFiles(dir1));
        assertEquals(0, countDirs(dir1));

        move( dir2,  dir2In1);

        assertEquals(6, countFiles(dir1));
        assertEquals(4, countDirs(dir1));
        assertEquals(7, countFiles(testDir));
        assertEquals(5, countDirs(testDir));

    }

    @Test
    @DisplayName("Test content of file")
    void testContentOfFile() throws IOException {
        String file3 = "src/test/resource/Dir2/Dir4/file3.txt";
        FileInputStream inputFileStream = new FileInputStream(file3);
        byte[] contentFile = inputFileStream.readAllBytes();
        inputFileStream.close();
        assertEquals(32, contentFile.length);

    }

}
