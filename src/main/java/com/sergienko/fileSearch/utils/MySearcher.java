package com.sergienko.fileSearch.utils;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MySearcher {

    public static void searchFiles(File rootDirectory, List<File> filesList) {

        if (rootDirectory.isDirectory()) {
            File[] directoryFiles = rootDirectory.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        searchFiles(file, filesList);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".txt")) {
                            filesList.add(new File(String.valueOf(file.getAbsoluteFile())));
                        }
                    }
                }
            }
        }
    }

    public static void sortList(List<File> fileList) {

        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
