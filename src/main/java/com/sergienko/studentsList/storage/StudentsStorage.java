package com.sergienko.studentsList.storage;

import com.sergienko.studentsList.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsStorage implements IStudentsStorage {

    private final String url;
    public static Connection connection;

    public StudentsStorage(String url) throws ClassNotFoundException {
        this.url = url;

        Class.forName("org.sqlite.JDBC");
    }

    @Override
    public void connectToDB() throws SQLException {
        connection = DriverManager.getConnection(url); //jdbc:sqlite:C:/sqlite/db.s3db //jdbc:sqlite:db.db
    }

    @Override
    public void createDB() throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement("DROP TABLE IF EXISTS students")) {
            ps.execute();
        }
        try (PreparedStatement ps = connection.prepareStatement("CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, full_name VARCHAR(255), birth_date VARCHAR (15), group_name VARCHAR (25))")) {
            ps.execute();
        }

    }

    @Override
    public void fillDB() throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO students (full_name, birth_date, group_name) VALUES ('Сергиенко Олег Валерьевич', '1991.10.20', 'M08-1')")) {
            ps.execute();
        }

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO students (full_name, birth_date, group_name) VALUES ('Савинский Андрей Владимирович', '1990.09.12', 'M08-2')")) {
            ps.execute();
        }
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {

        List<Student> students = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM students")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(rs));
                }
            }
        }

        return students;
    }

    @Override
    public void addStudent(String fullName, String dateBirth, String groupName) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO students (full_name, birth_date, group_name) VALUES (?, ?, ?)")) {
            ps.setString(1, fullName);
            ps.setString(2, dateBirth);
            ps.setString(3, groupName);
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteStudent(int id) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM students WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void closeDB() throws SQLException {
        connection.close();
    }
}
