package com.sergienko.studentsList.service;

import com.sergienko.studentsList.model.Student;
import com.sergienko.studentsList.storage.IStudentsStorage;
import com.sergienko.studentsList.storage.StudentsStorage;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentsApi {

    public void startApp() throws SQLException, ClassNotFoundException {
        IStudentsStorage dataBase = new StudentsStorage("jdbc:sqlite:db.db");

        dataBase.connectToDB();
        dataBase.createDB();
        dataBase.fillDB();


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Показать список студентов");
            System.out.println("2. Добавить студента");
            System.out.println("3. Удалить студента");
            System.out.println("4. Выйти");
            System.out.print("Введите команду: ");
            String command = scanner.nextLine();

            if (command.equals("1")) {
                System.out.println("");
                List<Student> students = dataBase.getAllStudents();
                System.out.format("+-----+-------------------------------------------+---------------+--------+\n");
                System.out.format("|  №  |               ФИО                         | Дата рождения | Группа |\n");
                System.out.format("+-----+-------------------------------------------+---------------+--------+\n");
                for (Student student : students) {
                    System.out.format("| %3s | %-41s | %13s | %6s |%n",
                            student.getId(),
                            student.getFullName(),
                            student.getBirthDate(),
                            student.getGroup());
                }
                System.out.format("+-----+-------------------------------------------+---------------+--------+\n\n");

            } else if (command.equals("2")) {
                String fullName = "", dateBirth = "", groupName = "";
                System.out.println("Введите ФИО студента: ");
                fullName = scanner.nextLine();
                while (!dateBirth.matches("[1-2](0|9)\\d{2}[.](0[1-9]|1[0-2]).(0[1-9]|[12][0-9]|3[01])")) {
                    System.out.println("Введите дату рождения студента в формате ГГГГ.ММ.ДД: ");
                    dateBirth = scanner.nextLine();
                    if (!dateBirth.matches("[1-2](0|9)\\d{2}[.](0[1-9]|1[0-2]).(0[1-9]|[12][0-9]|3[01])"))
                        System.err.println("Некорректный формат даты. Пожалуйста вводите дату в формате ГГГГ.ММ.ДД");
                }
                System.out.println("Введите название группы студента: ");
                groupName = scanner.nextLine();

                dataBase.addStudent(fullName, dateBirth, groupName);

                System.out.println("Новый студент добавлен в базу.\n");

            } else if (command.equals("3")) {
                String studentId = "";
                while (!studentId.matches("\\d+")) {
                    System.out.println("Введите уникальный номер студента: ");
                    studentId = scanner.nextLine();
                    if (!studentId.matches("\\d+"))
                        System.err.println("Некорректная команда: введите цифровое значение.");
                }
                dataBase.deleteStudent(Integer.parseInt(studentId));
                System.out.println("Студент с уникальным номером " + studentId + " удалён из базы.\n");

            } else if (command.equals("4")) {

                dataBase.closeDB();
                System.exit(0);

            } else {
                System.err.println("Некорректная команда: введите цифру от 1 до 4.");
            }
        }
    }
}
