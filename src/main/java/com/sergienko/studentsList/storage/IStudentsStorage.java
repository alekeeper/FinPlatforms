package com.sergienko.studentsList.storage;

import com.sergienko.studentsList.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentsStorage {

    void connectToDB() throws ClassNotFoundException, SQLException;

    void createDB() throws SQLException;

    void fillDB() throws SQLException;

    void closeDB() throws SQLException;

    List<Student> getAllStudents() throws SQLException;

    void addStudent(String fullName, String dateBirth, String groupName) throws SQLException;

    void deleteStudent(int id) throws SQLException;
}
