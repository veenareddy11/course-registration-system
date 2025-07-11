// RegistrationService.java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationService {

    // Register a student for a course
    public void registerStudentToCourse(int studentId, int courseId) {
        try (Connection conn = DatabaseConnection.connect()) {

            // First check if student exists
            if (!recordExists(conn, "students", "student_id", studentId)) {
                System.out.println("Student ID not found.");
                return;
            }

            // Check if course exists
            if (!recordExists(conn, "courses", "course_id", courseId)) {
                System.out.println("Course ID not found.");
                return;
            }

            // Check if already registered
            String checkSql = "SELECT * FROM registrations WHERE student_id = ? AND course_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, studentId);
            checkStmt.setInt(2, courseId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Student already registered for this course.");
                return;
            }

            // Register the student
            String sql = "INSERT INTO registrations (student_id, course_id) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student registered for course!");
            } else {
                System.out.println("Failed to register student.");
            }

        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
    }

    // View all registrations
    public void viewAllRegistrations() {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = """
                SELECT s.student_id, s.student_name, c.course_id, c.course_name
                FROM registrations r
                JOIN students s ON r.student_id = s.student_id
                JOIN courses c ON r.course_id = c.course_id
                """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\nAll Registrations:");
            while (rs.next()) {
                System.out.println("- Student: " + rs.getString("student_name")
                        + " (ID: " + rs.getInt("student_id") + ")"
                        + " - Course: " + rs.getString("course_name")
                        + " (ID: " + rs.getInt("course_id") + ")");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving registrations: " + e.getMessage());
        }
    }

    // Utility method to check if a record exists
    private boolean recordExists(Connection conn, String table, String column, int value) throws SQLException {
        String sql = "SELECT * FROM " + table + " WHERE " + column + " = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, value);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }
}
