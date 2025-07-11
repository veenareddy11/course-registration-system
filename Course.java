// Course.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Course {
    private int courseId;
    private String courseName;
    private String instructor;

    // Constructor
    public Course(int courseId, String courseName, String instructor) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
    }

    // Getters
    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    // Setters
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    // toString
    @Override
    public String toString() {
        return "Course ID: " + courseId + ", Name: " + courseName + ", Instructor: " + instructor;
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
