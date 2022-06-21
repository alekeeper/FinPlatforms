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

    public static void saveToFile(String pathToNewFile) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(pathToNewFile + "\\newFile.txt", true)))) {
            writer.println(allLines);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nИмя файла: \"newFile.txt\"");
    }
}

