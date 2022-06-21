package com.sergienko.fileSearch;

import com.sergienko.fileSearch.utils.MyReader;
import com.sergienko.fileSearch.utils.MySearcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        List<File> fileList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите адрес директории: ");
        String rootDirectory = scanner.nextLine();

        System.out.println("Введите адрес для сохранения файла: ");
        String pathToNewFile = scanner.nextLine();

        MySearcher.searchFiles(new File(rootDirectory), fileList); //C:\\FinPlatforms
        MySearcher.sortList(fileList);

        MyReader.readFiles(fileList);
        MyReader.saveToFile(pathToNewFile); //C:\FinPlatforms\src\main\java\com\sergienko\fileSearch

    }
}
