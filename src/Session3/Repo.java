package Session3;

import java.sql.*;

public class Repo {


    public void createEmployeeTable() throws SQLException {
        String sql = """
            CREATE TABLE  IF NOT EXISTS EMPLOYEE 
            (Id INTEGER PRIMARY KEY AUTO_INCREMENT,
            Name VARCHAR(21) NOT NULL , 
            
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


    public void insertEmployee(String name,String dep , Long salary) throws SQLException {
        String sql = """
            INSERT INTO EMPLOYEE 
            ( Name, Department, Salary) VALUES  (?, ?, ?); 
            """;

        try( Connection conn = DBUtil.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, dep);
            ps.setLong(3, salary);
            ps.executeUpdate();


        }catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }

    public void findEmployeeById(String id) throws SQLException {
        String sql= """
                SELECT * FROM EMPLOYEE WHERE Id = ?;
                """;
        try( Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("Name"));

                System.out.println(rs.getString("Department"));
                System.out.println(rs.getString("Salary"));

            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listAllEmployees() throws SQLException {
        String sql= """
                SELECT * FROM EMPLOYEE;
                """;
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int counter = 1;
            while (rs.next()) {
                System.out.println("EMPLOYEE " + counter++);
                System.out.println(rs.getString("Name"));
                System.out.println(rs.getString("Department"));
                System.out.println(rs.getString("Salary"));


            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateEmployeeSalaryAndDepartment(int id,String dep , Long salary) throws SQLException {
        String sql= """
                UPDATE EMPLOYEE SET Salary = ? , SET Department = ? WHERE Id = ?;
                """;

        try( Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, salary);
            ps.setString(2, dep);
            ps.setInt(3, id);
            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void  findEmployeeById(int id) throws SQLException {
        String sql= """
                
                SELECT * FROM EMPLOYEE WHERE Id = ?;
                """;
        try( Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("Name"));

                System.out.println(rs.getString("Department"));
                System.out.println(rs.getString("Salary"));
            } else {
                System.out.println("Employee not found");
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteEmployeeById(int id) throws SQLException {
        String sql= """
                DELETE FROM EMPLOYEE WHERE Id = ?;
                """;
        try( Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getEmployeeAnalyticsByDepartment() throws SQLException {
        String sql= """
                SELECT Department ,SUM(SALARY) FROM EMPLOYEE GROUP BY Department;
                """;

        try( Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("Department"));
                System.out.println(rs.getString("Salary"));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
