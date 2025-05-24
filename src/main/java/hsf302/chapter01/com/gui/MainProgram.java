package hsf302.chapter01.com.gui;

import hsf302.chapter01.com.pojo.Student;
import hsf302.chapter01.com.pojo.Subject;
import hsf302.chapter01.com.service.StudentService;
import hsf302.chapter01.com.service.StudentServiceImpl;

import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {

        StudentService studentService = new StudentServiceImpl();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add new student");
            System.out.println("2. View all students");
            System.out.println("3. Find student by ID");
            System.out.println("4. Update student");
            System.out.println("5. Delete student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:

                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    Student newStudent = new Student();
                    newStudent.setName(name);
                    newStudent.setPassword(password);
                    Subject math = new Subject("Math", "Room 101");
                    Subject java = new Subject("Java", "Room 102");

                    newStudent.getSubjects().add(math);
                    newStudent.getSubjects().add(java);
                    studentService.save(newStudent);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    System.out.println("\nAll Students:");
                    studentService.findAll().forEach(s -> {
                        System.out.println("ID: " + s.getStudent_id() +
                                ", Name: " + s.getName() +
                                ", Password: " + s.getPassword());
                        System.out.println("Subjects:");
                        s.getSubjects().forEach(subject ->
                                System.out.println("  - " + subject.getSubject_name() +
                                        " (Location: " + subject.getLocation() + ")"));
                        System.out.println("---------------");
                    });
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    Long searchId = scanner.nextLong();
                    Student found = studentService.findById(searchId);
                    if (found != null) {
                        System.out.println("ID: " + found.getStudent_id() +
                                ", Name: " + found.getName() +
                                ", Password: " + found.getPassword());

                        System.out.println("Subjects:");
                        found.getSubjects().forEach(subject ->
                                System.out.println("  - " + subject.getSubject_name() +
                                        " (Location: " + subject.getLocation() + ")"));
                        System.out.println("---------------");
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;


                case 4:
                    System.out.print("Enter student ID to update: ");
                    Long updateId = scanner.nextLong();
                    scanner.nextLine(); // Consume newline
                    Student existingStudent = studentService.findById(updateId);
                    if (existingStudent != null) {
                        System.out.print("Enter new name: ");
                        existingStudent.setName(scanner.nextLine());
                        System.out.print("Enter new password: ");
                        existingStudent.setPassword(scanner.nextLine());

                        // Clear existing subjects
                        existingStudent.getSubjects().clear();

                        // Add new subjects
                        System.out.println("Replacing subjects...");
                        System.out.print("Enter first subject name: ");
                        String subject1Name = scanner.nextLine();
                        System.out.print("Enter first subject location: ");
                        String subject1Location = scanner.nextLine();
                        Subject subject1 = new Subject(subject1Name, subject1Location);

                        System.out.print("Enter second subject name: ");
                        String subject2Name = scanner.nextLine();
                        System.out.print("Enter second subject location: ");
                        String subject2Location = scanner.nextLine();
                        Subject subject2 = new Subject(subject2Name, subject2Location);

                        // Add subjects to student
                        existingStudent.getSubjects().add(subject1);
                        existingStudent.getSubjects().add(subject2);

                        studentService.update(updateId, existingStudent);
                        System.out.println("Student and subjects updated successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter student ID to delete: ");
                    Long deleteId = scanner.nextLong();
                    Student studentToDelete = studentService.findById(deleteId);
                    if (studentToDelete != null) {
                        studentService.delete(deleteId);
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}

//8D81-ABF4