
import android.annotation.TargetApi;
import android.os.Build;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;   // Use classes in java.sql package
import java.sql.Statement;

public class databaseManagement {
	
//	@TargetApi(Build.VERSION_CODES.KITKAT)
//	public static void username_authentication(String username, String password){
//		String usernameIdentification = "";
//		String passwordIdentification = "";
//
//		// Step 1: Allocate a database "Connection" object
//		try(
//			Connection conn = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/blackjacked", "rouser", "temp");
//
//			// Step 2: Allocate a "Statement" object in Connection
//			Statement stmt = conn.createStatement();
//		   ){
//			//Step 3: Execute a SQL SELECT query, the query result
//			// is returned in a "ResultSet" object.
//			String strSelect = "SELECT username FROM blackjackdb.authentication WHERE username = '" + username + "'";
//			System.out.println("The SQL query is: " + strSelect); // Echo For debugging
//	        System.out.println();
//	        ResultSet rset = stmt.executeQuery(strSelect);
//
//	        // Step 4: Process the ResultSet by scrolling the cursor forward via rset.next()
//	        // If username exists proceed with further verification
//	        System.out.println("Scanning records...");
//	        rset.next();
//	        usernameIdentification = rset.getString("username");
//
//	        if(username.equals(usernameIdentification)){
//	        	System.out.println("User found: " + username + " is located @" + usernameIdentification
//	        			+ " verifying password confirmation.");
//
//	        	strSelect = "SELECT username, password FROM blackjackdb.authentication WHERE username = '" + username + "'";
//	        	System.out.println(strSelect);
//	        	rset = stmt.executeQuery(strSelect);
//	        	rset.next();
//	        	passwordIdentification = rset.getString("password");
//	        	if(password.equals(passwordIdentification)){
//	        		System.out.println("System Login Successful.");
//	        	}
//	        	else{
//	        		System.out.println("Password Incorrect.");
//	        	}
//	        }
//	        else{
//	        	System.out.println("User not found!");
//	        }
//
//
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
//
//	// Need getters/setters of attributes: win/loss ratio, money, and best hand.
//
//	public static void main(String[] args){
////		username_authentication("user2", "user2");
//	}
}
