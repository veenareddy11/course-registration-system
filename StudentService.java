// StudentService.java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentService {

    // Add new student to the database
    public void addStudent(Student student) {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO students (student_id, student_name) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student.getStudentId());
            stmt.setString(2, student.getStudentName());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student added successfully!");
            } else {
                System.out.println("Failed to add student.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // View all students
    public void viewAllStudents() {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT * FROM students";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\nAll Registered Students:");
            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("student_name");
                System.out.println("- ID: " + id + ", Name: " + name);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }
    }
    // Delete a student by ID
public void deleteStudent(int studentId) {
    try (Connection conn = DatabaseConnection.connect()) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, studentId);

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student ID not found.");
        }

    } catch (SQLException e) {
        System.out.println("Error deleting student: " + e.getMessage());
    }
}

}
