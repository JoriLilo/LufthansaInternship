package Session3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {


    static String url = "jdbc:mysql://localhost:3306/javaDB";
    static String user = "root";
    static String password = "Root123.";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }




}
