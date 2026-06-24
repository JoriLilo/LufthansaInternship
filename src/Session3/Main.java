package Session3;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Repo repo = new Repo();

        System.out.println("=== JDBC CRUD Application ===\n");

        // 1. Create table
        repo.createEmployeeTable();

        // 2. Insert employees
        repo.insertEmployee("John Doe", "IT", (long) 75000.00);
        repo.insertEmployee("Jane Smith", "HR", (long) 65000.00);
        repo.insertEmployee("Bob Johnson", "IT", (long) 80000.00);
        repo.insertEmployee("Alice Williams", "Finance", (long) 70000.00);
        repo.insertEmployee("Charlie Brown", "IT", (long) 72000.00);
        repo.insertEmployee("Diana Prince", "HR", (long) 68000.00);

        // 3. Find employee by id
        repo.findEmployeeById(3);

        // 4. List all employees
        repo.listAllEmployees();

        // 5. Update employee salary and department
        repo.updateEmployeeSalaryAndDepartment(2, "Finance", (long) 72000.00);

        // 6. Show updated employee
        repo.findEmployeeById(2);

        // 7. Analytics: count and average salary by department
        repo.getEmployeeAnalyticsByDepartment();

        // 8. Delete employee
        repo.deleteEmployeeById(5);

        // 9. List all employees after deletion
        repo.listAllEmployees();

        System.out.println("\n=== Application completed ===");
    }
}