package com.sergienko.fileSearch.utils;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class MyReader {
    static String allLines = "";

    public static void readFiles(List<File> list) {
        try {

            StringBuilder sb = new StringBuilder();

            for (File file : list) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String currentLine;
                    while ((currentLine = br.readLine()) != null) {
                        sb.append(currentLine).append("\n");
                    }
                }
            }

            allLines = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile (String pathToNewFile) {

        File newFile = new File(pathToNewFile + "\\newFile.txt"); //C:\FinPlatforms\src\main\java\com\sergienko\fileSearch

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(newFile, true)));
            writer.println(allLines);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nИмя файла: \"newFile.txt\"");
    }
}

