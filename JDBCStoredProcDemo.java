import java.sql.*;

public class JDBCStoredProcDemo {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "testuser";
        String password = "Test@1234";

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect Database
            Connection conn =
                    DriverManager.getConnection(url, user, password);

            System.out.println("Database Connected Successfully");


            // Calling Insert Stored Procedure
            CallableStatement insertStmt =
                    conn.prepareCall("{call insert_employee(?, ?, ?)}");

            insertStmt.setInt(1, 101);
            insertStmt.setString(2, "John Doe");
            insertStmt.setDouble(3, 55000);

            insertStmt.execute();

            System.out.println("Employee Inserted Successfully");


            // Calling Salary Stored Procedure
            CallableStatement salaryStmt =
                    conn.prepareCall("{call get_salary_by_id(?, ?)}");

            salaryStmt.setInt(1, 101);

            salaryStmt.registerOutParameter(2, Types.DECIMAL);

            salaryStmt.execute();

            double salary = salaryStmt.getDouble(2);

            System.out.println("Employee Salary = " + salary);


            insertStmt.close();
            salaryStmt.close();
            conn.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
