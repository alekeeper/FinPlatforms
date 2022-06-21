package com.sergienko.studentsList.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {
    private final int id;
    private final String fullName;

    private final String birthDate;
    private final String group;

    public Student(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.fullName = rs.getString("full_name");
        this.birthDate = rs.getString("birth_date");
        this.group = rs.getString("group_name");
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGroup() {
        return group;
    }
}
