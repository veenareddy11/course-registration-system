// DatabaseConnection.java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/course_system";
        String user = "root"; 
        String password = "Veena@1711";

        return DriverManager.getConnection(url, user, password);
    }
}
