// Main.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        RegistrationService registrationService = new RegistrationService();

        int choice;
        do {
            System.out.println("\nCOURSE REGISTRATION SYSTEM");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Register Student to Course");
            System.out.println("4. View All Students");
            System.out.println("5. View All Courses");
            System.out.println("6. View All Registrations");
            System.out.println("7. Delete Student");
            System.out.println("8. Delete Course");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Student Name: ");
                    String sname = sc.nextLine();
                    Student s = new Student(sid, sname);
                    studentService.addStudent(s);
                }

                case 2 -> {
                    System.out.print("Enter Course ID: ");
                    int cid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Course Name: ");
                    String cname = sc.nextLine();
                    System.out.print("Enter Instructor Name: ");
                    String instructor = sc.nextLine();
                    Course c = new Course(cid, cname, instructor);
                    courseService.addCourse(c);
                }

                case 3 -> {
                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Enter Course ID: ");
                    int cid = sc.nextInt();
                    registrationService.registerStudentToCourse(sid, cid);
                }

                case 4 -> studentService.viewAllStudents();
                case 5 -> courseService.viewAllCourses();
                case 6 -> registrationService.viewAllRegistrations();
                case 7 -> {
                        System.out.print("Enter Student ID to delete: ");
                        int id = sc.nextInt();
                        studentService.deleteStudent(id);
                        }

                case 8 -> {
                        System.out.print("Enter Course ID to delete: ");
                        int id = sc.nextInt();
                        courseService.deleteCourse(id);
                        }

                case 0 -> System.out.println("Exiting. Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
