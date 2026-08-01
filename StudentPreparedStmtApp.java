import java.sql.*;

public class StudentPreparedStmtApp {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "testuser";
        String password = "Test@1234";

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect Database
            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Database Connected Successfully");


            // Create Student table
            String createTable = "CREATE TABLE IF NOT EXISTS Student ("
                    + "RollNo INT PRIMARY KEY, "
                    + "Name VARCHAR(50), "
                    + "Address VARCHAR(100))";

            con.createStatement().executeUpdate(createTable);

            System.out.println("Table created successfully.");


            // Clear old records
            con.createStatement().executeUpdate("DELETE FROM Student");


            // Insert initial records
            Statement stmt = con.createStatement();

            stmt.executeUpdate(
                    "INSERT INTO Student VALUES (1,'Ravi','Hyderabad')");

            stmt.executeUpdate(
                    "INSERT INTO Student VALUES (2,'Sita','Chennai')");

            stmt.executeUpdate(
                    "INSERT INTO Student VALUES (3,'Kiran','Bangalore')");


            System.out.println("Initial records inserted.");


            // Display initial records
            System.out.println("\nInitial Records:");
            displayRecords(con);



            // Insert two records using PreparedStatement
            String insertSQL =
                    "INSERT INTO Student (RollNo, Name, Address) VALUES (?, ?, ?)";

            PreparedStatement insertStmt =
                    con.prepareStatement(insertSQL);


            insertStmt.setInt(1, 6);
            insertStmt.setString(2, "Meena");
            insertStmt.setString(3, "Pune");
            insertStmt.executeUpdate();


            insertStmt.setInt(1, 7);
            insertStmt.setString(2, "Ramesh");
            insertStmt.setString(3, "Mumbai");
            insertStmt.executeUpdate();


            System.out.println("Two new records inserted.");



            // Update record
            String updateSQL =
                    "UPDATE Student SET Address=? WHERE RollNo=?";

            PreparedStatement updateStmt =
                    con.prepareStatement(updateSQL);


            updateStmt.setString(1, "Delhi");
            updateStmt.setInt(2, 2);

            updateStmt.executeUpdate();

            System.out.println("One record updated.");



            // Delete record
            String deleteSQL =
                    "DELETE FROM Student WHERE RollNo=?";

            PreparedStatement deleteStmt =
                    con.prepareStatement(deleteSQL);


            deleteStmt.setInt(1, 3);

            deleteStmt.executeUpdate();

            System.out.println("One record deleted.");



            // Display final records
            System.out.println("\nFinal Records:");
            displayRecords(con);


            con.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }



    // Display records using PreparedStatement
    public static void displayRecords(Connection con)
            throws SQLException {


        String selectSQL = "SELECT * FROM Student";

        PreparedStatement ps =
                con.prepareStatement(selectSQL);


        ResultSet rs = ps.executeQuery();


        System.out.println("RollNo\tName\tAddress");


        while(rs.next()) {

            int roll = rs.getInt("RollNo");
            String name = rs.getString("Name");
            String address = rs.getString("Address");


            System.out.println(
                    roll + "\t" + name + "\t" + address);
        }

        rs.close();
        ps.close();
    }
}
