import java.sql.*;

public class JDBCApp {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "testuser";
        String password = "Test@1234";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection con = DriverManager.getConnection(url, user, password);

            Statement stmt = con.createStatement();

            // Create Table
            String createTable = "CREATE TABLE IF NOT EXISTS Student ("
                    + "RollNo INT PRIMARY KEY, "
                    + "Name VARCHAR(50), "
                    + "Address VARCHAR(100))";

            stmt.executeUpdate(createTable);
            System.out.println("Table created successfully.");

            // Clear old records to avoid duplicate primary key error
            stmt.executeUpdate("DELETE FROM Student");

            // Insert initial records
            stmt.executeUpdate(
                    "INSERT INTO Student VALUES (1, 'Ravi', 'Hyderabad')");
            stmt.executeUpdate(
                    "INSERT INTO Student VALUES (2, 'Sita', 'Chennai')");
            stmt.executeUpdate(
                    "INSERT INTO Student VALUES (3, 'Kiran', 'Bangalore')");

            System.out.println("Initial records inserted.");

            // Display initial records
            System.out.println("\nInitial Records:");
            displayRecords(stmt);


            // Insert two new records
            stmt.executeUpdate(
                    "INSERT INTO Student VALUES (4, 'Meena', 'Pune')");
            stmt.executeUpdate(
                    "INSERT INTO Student VALUES (5, 'Ramesh', 'Mumbai')");

            System.out.println("\nTwo new records inserted.");


            // Update one record
            stmt.executeUpdate(
                    "UPDATE Student SET Address='Delhi' WHERE RollNo=2");

            System.out.println("One record updated.");


            // Delete one record
            stmt.executeUpdate(
                    "DELETE FROM Student WHERE RollNo=3");

            System.out.println("One record deleted.");


            // Display final records
            System.out.println("\nFinal Records:");
            displayRecords(stmt);


            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Function to display records
    public static void displayRecords(Statement stmt) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM Student");

        System.out.println("RollNo\tName\tAddress");

        while (rs.next()) {

            int roll = rs.getInt("RollNo");
            String name = rs.getString("Name");
            String address = rs.getString("Address");

            System.out.println(
                    roll + "\t" + name + "\t" + address
            );
        }

        rs.close();
    }
}
