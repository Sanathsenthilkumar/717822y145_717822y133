package a_java;

import java.sql.*;

public class EmployeePayrollManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employeepayrollsystem";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "San@SQL1";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            createEmployeePayrollTable(statement);
            insertSampleData(statement);
            displayEmployees(statement);
            updateEmployeeSalary(statement, 1, 5500.00);
            displayEmployees(statement);
            deleteEmployee(statement, 2);
            displayEmployees(statement);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createEmployeePayrollTable(Statement statement) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS EmployeePayroll (" +
                "EmployeeID INT PRIMARY KEY AUTO_INCREMENT," +
                "FirstName VARCHAR(50)," +
                "LastName VARCHAR(50)," +
                "DateOfBirth DATE," +
                "Gender VARCHAR(10)," +
                "Department VARCHAR(50)," +
                "Position VARCHAR(50)," +
                "EmployeeType VARCHAR(20)," +
                "ContactInfo VARCHAR(100)," +
                "HireDate DATE," +
                "TerminationDate DATE," +
                "SalaryAmount DECIMAL(10,2)," +
                "PaymentFrequency VARCHAR(20)," +
                "PayGrade VARCHAR(10)," +
                "PaymentMethod VARCHAR(50)," +
                "TransactionDate DATE," +
                "TransactionType VARCHAR(50)," +
                "TransactionAmount DECIMAL(10,2)," +
                "TransactionDescription TEXT)";
        statement.executeUpdate(createTableSQL);
    }

    private static void insertSampleData(Statement statement) throws SQLException {
        String insertSQL = "INSERT INTO EmployeePayroll (FirstName, LastName, DateOfBirth, Gender, Department, Position, EmployeeType, ContactInfo, HireDate, SalaryAmount, PaymentFrequency, PayGrade, PaymentMethod, TransactionDate, TransactionType, TransactionAmount, TransactionDescription) VALUES" +
                "('John', 'Doe', '1990-05-15', 'Male', 'Human Resources', 'HR Manager', 'Full-time', 'john.doe@example.com', '2020-01-15', 5000.00, 'Monthly', 'Grade 7', 'Direct Deposit', '2024-04-01', 'Salary Payment', 5000.00, 'Monthly salary payment for April 2024')," +
                "('Jane', 'Smith', '1985-08-20', 'Female', 'Finance', 'Senior Accountant', 'Full-time', 'jane.smith@example.com', '2018-06-10', 4500.00, 'Monthly', 'Grade 6', 'Bank Transfer', '2024-04-05', 'Salary Payment', 4500.00, 'Monthly salary payment for April 2024')," +
                "('Michael', 'Johnson', '1995-03-10', 'Male', 'Marketing', 'Marketing Specialist', 'Full-time', 'michael.johnson@example.com', '2021-02-20', 4000.00, 'Monthly', 'Grade 5', 'Check', '2024-04-10', 'Salary Payment', 4000.00, 'Monthly salary payment for April 2024')";
        statement.executeUpdate(insertSQL);
    }

    private static void displayEmployees(Statement statement) throws SQLException {
        String selectSQL = "SELECT * FROM EmployeePayroll";
        ResultSet resultSet = statement.executeQuery(selectSQL);

        System.out.println("\nEmployee Data:");
        System.out.println("*****************************************************************************************************************************************************************************************************************");
        System.out.printf("%-10s %-15s %-15s %-15s %-10s %-20s %-20s %-15s %-20s %-12s %-12s %-12s %-15s\n",
                "EmployeeID", "FirstName", "LastName", "DateOfBirth", "Gender", "Department", "Position", "EmployeeType", "ContactInfo", "HireDate", "TerminationDate", "SalaryAmount", "PaymentFrequency");
        System.out.println("*****************************************************************************************************************************************************************************************************************");
        while (resultSet.next()) {
            System.out.printf("%-10d %-15s %-15s %-15s %-10s %-20s %-20s %-15s %-20s %-12s %-12s %-12.2f %-15s\n",
                    resultSet.getInt("EmployeeID"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getDate("DateOfBirth"),
                    resultSet.getString("Gender"),
                    resultSet.getString("Department"),
                    resultSet.getString("Position"),
                    resultSet.getString("EmployeeType"),
                    resultSet.getString("ContactInfo"),
                    resultSet.getDate("HireDate"),
                    resultSet.getString("TerminationDate"),
                    resultSet.getDouble("SalaryAmount"),
                    resultSet.getString("PaymentFrequency"));
        }
        System.out.println("*****************************************************************************************************************************************************************************************************************");
    }

    private static void updateEmployeeSalary(Statement statement, int employeeID, double newSalaryAmount) throws SQLException {
        String updateSQL = "UPDATE EmployeePayroll SET SalaryAmount = " + newSalaryAmount + " WHERE EmployeeID = " + employeeID;
        statement.executeUpdate(updateSQL);
        System.out.println("Employee salary updated successfully.");
    }

    private static void deleteEmployee(Statement statement, int employeeID) throws SQLException {
        String deleteSQL = "DELETE FROM EmployeePayroll WHERE EmployeeID = " + employeeID;
        statement.executeUpdate(deleteSQL);
        System.out.println("Employee deleted successfully.");
    }
}