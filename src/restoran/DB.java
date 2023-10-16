package restoran;
import java.sql.*;
import javax.swing.JOptionPane;
public class DB {
    private static Connection conn;
    public static Connection connectdb() throws SQLException {
        String DB = "jdbc:mysql://localhost:3306/restoran";
        String username = "root";
        String password = "";
        
        try {
            conn = ( Connection ) DriverManager.getConnection(DB, username, password);
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return conn;
    }
    
}
