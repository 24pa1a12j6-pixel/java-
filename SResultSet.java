import java.sql.*;

public class SResultSet {

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


            // Create Scrollable ResultSet
            Statement st = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);


            // Execute Query
            ResultSet rs =
                    st.executeQuery("SELECT * FROM Student");


            System.out.println("\nRecords in Forward Direction:");
            System.out.println("RollNo\tName\tAddress");
            System.out.println("--------------------------------");


            while(rs.next()) {

                System.out.println(
                        rs.getInt("RollNo") + "\t" +
                        rs.getString("Name") + "\t" +
                        rs.getString("Address"));
            }



            System.out.println("\nRecords in Backward Direction:");
            System.out.println("RollNo\tName\tAddress");
            System.out.println("--------------------------------");


            while(rs.previous()) {

                System.out.println(
                        rs.getInt("RollNo") + "\t" +
                        rs.getString("Name") + "\t" +
                        rs.getString("Address"));
            }



            // First Record
            rs.first();

            System.out.println("\nFirst Record:");

            System.out.println(
                    "Row " + rs.getRow() + ": " +
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t" +
                    rs.getString("Address"));



            // Last Record
            rs.last();

            System.out.println("\nLast Record:");

            System.out.println(
                    "Row " + rs.getRow() + ": " +
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t" +
                    rs.getString("Address"));



            // Second Record from Last
            rs.relative(-1);

            System.out.println("\n2nd Record from Last:");

            System.out.println(
                    "Row " + rs.getRow() + ": " +
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t" +
                    rs.getString("Address"));



            // Second Record from Beginning
            rs.absolute(2);

            System.out.println("\n2nd Record from Beginning:");

            System.out.println(
                    "Row " + rs.getRow() + ": " +
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t" +
                    rs.getString("Address"));



            // Close resources
            rs.close();
            st.close();
            con.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
