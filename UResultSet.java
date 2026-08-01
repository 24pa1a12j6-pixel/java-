import java.sql.*;

public class UResultSet {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "testuser";
        String password = "Test@1234";

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");


            // Connect Database
            Connection con =
                    DriverManager.getConnection(url, user, password);

            System.out.println("Database Connected Successfully");


            // Create scrollable and updatable ResultSet
            Statement st = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);



            // Fetch Student table
            ResultSet rs =
                    st.executeQuery("SELECT * FROM Student");



            // Delete last row
            rs.last();

            rs.deleteRow();

            System.out.println(
                    "Last student record deleted successfully.");



            // Insert new row
            rs.moveToInsertRow();

            rs.updateInt("RollNo", 105);
            rs.updateString("Name", "John Doe");
            rs.updateString("Address", "Hyderabad");

            rs.insertRow();

            System.out.println(
                    "New student record inserted successfully.");



            // Display updated table
            rs = st.executeQuery("SELECT * FROM Student");


            System.out.println("\nUpdated Student Table:");
            System.out.println("RollNo\tName\tAddress");
            System.out.println("--------------------------------");


            while(rs.next()) {

                System.out.println(
                        rs.getInt("RollNo") + "\t" +
                        rs.getString("Name") + "\t" +
                        rs.getString("Address"));
            }



            rs.close();
            st.close();
            con.close();

        }
        catch(Exception e) {

            e.printStackTrace();

        }
    }
}
