package com.sergienko.studentsList;

import com.sergienko.studentsList.service.StudentsApi;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new StudentsApi().startApp();
    }
}
