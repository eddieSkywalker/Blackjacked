//import java.sql.*;   // Use classes in java.sql package
//
//public class databaseTest {
//	public static void main(String[] args){
//		try(
//			// Step 1: Allocate a database "Connection" object
//			Connection conn = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/blackjackdb", "root", "mypass");
//			
//			// Step 2: Allocate a "Statement" object in the Connection
//			Statement stmt = conn.createStatement();	
//		)  {
//			// Step 3: Execute a SQL SELECT query, the query result
//			//  is returned in a "ResultSet" object.
//			String strSelect = "SELECT * FROM authentication";
//			System.out.println("The SQL query is: " + strSelect); // Echo For debugging
//			System.out.println();
//
//			ResultSet rset = stmt.executeQuery(strSelect);
// 
//		    // Step 4: Process the ResultSet by scrolling the cursor forward via next().
//	        //  For each row, retrieve the contents of the cells with getXxx(columnName).
//	        System.out.println("The records selected are:");
//	        int rowCount = 0;
//	        while(rset.next()) 
//	        {   // Move the cursor to the next row
//	            String username = rset.getString("username");
//	            String password = rset.getString("password");
//	            int    id   = rset.getInt("id");
//	            System.out.println(username + ", " + password + ", " + id);
//	            ++rowCount;
//	        }
//	        
//	        System.out.println("Total number of records = " + rowCount);
// 
//      } catch(SQLException ex) {
//         ex.printStackTrace();
//      }
//		
//      // Step 5: Close the resources - Done automatically by try-with-resources
//	   }
//	}
