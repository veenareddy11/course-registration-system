// CourseService.java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseService {

    // Add a new course
    public void addCourse(Course course) {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO courses (course_id, course_name, instructor) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course.getCourseId());
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getInstructor());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Course added successfully!");
            } else {
                System.out.println("Failed to add course.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    // View all available courses
    public void viewAllCourses() {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT * FROM courses";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\nAvailable Courses:");
            while (rs.next()) {
                int id = rs.getInt("course_id");
                String name = rs.getString("course_name");
                String instructor = rs.getString("instructor");
                System.out.println("- ID: " + id + ", Name: " + name + ", Instructor: " + instructor);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving courses: " + e.getMessage());
        }
    }
    // Delete a course by ID
public void deleteCourse(int courseId) {
    try (Connection conn = DatabaseConnection.connect()) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, courseId);

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("Course ID not found.");
        }

    } catch (SQLException e) {
        System.out.println("Error deleting course: " + e.getMessage());
    }
}
}
