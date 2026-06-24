package Session3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Repo {


    public void createEmployeeTable() throws SQLException {
        String sql = """
            CREATE TABLE  IF NOT EXIST EMPLOYEE 
            (Id INTEGER PRIMARY KEY AUTOINCREMENT,
            FirstName VARCHAR(21) NOT NULL , 
            LastName VARCHAR(21) NOT NULL, 
            Department VARCHAR(16) NOT NULL, 
            Salary LONG NOT NULL)
            """;

        try( Connection conn = DBUtil.getConnection()) {


           Statement stmt = conn.createStatement();
           stmt.execute(sql);

        }catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }


    public void insertEmployee(String firstNAme, String lastName, String dep , Long salary) throws SQLException {
        String sql = """
            INSERT INTO EMPLOYEE 
            ( FirstName, LastName , Department, Salary) VALUES  (?, ?, ?, ?); 
            """;

        try( Connection conn = DBUtil.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, firstNAme);
            ps.setString(2, lastName);
            ps.setString(3, dep);
            ps.setLong(4, salary);
            ps.executeUpdate();


        }catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }


}
