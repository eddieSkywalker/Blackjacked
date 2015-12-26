package electrifyingstudios.blackjacked.com;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity
{
    private static final String TAG = "MyAct";
    private static final String AUTHENTICATE = "Authentication";
    private static final String SERVER_CONTACT = "Contacting Server...";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "Login Activity activated ");
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void username_authentication(View v)
    {
        EditText editTextEmail = (EditText) findViewById(R.id.email);
        EditText editTextPassword = (EditText) findViewById(R.id.password);
        String username = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        Log.d(AUTHENTICATE, "Auth method accessed | Login Activity activated ");

        String usernameIdentification = "";
        String passwordIdentification = "";

        // Step 1: Allocate a database "Connection" object
        try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/blackjackdb", "rouser", "temp");

                // Step 2: Allocate a "Statement" object in Connection
                Statement stmt = conn.createStatement()
        ){
            Log.d(SERVER_CONTACT, "Database access attempted | Auth method accessed | Login Activity activated ");
            //Step 3: Execute a SQL SELECT query, the query result
            // is returned in a "ResultSet" object.
            String strSelect = "SELECT username FROM blackjackdb.authentication WHERE username = '" + username + "'";
            System.out.println("The SQL query is: " + strSelect); // Echo For debugging
            System.out.println();
            ResultSet rset = stmt.executeQuery(strSelect);

            // Step 4: Process the ResultSet by scrolling the cursor forward via rset.next()
            // If username exists proceed with further verification
            System.out.println("Scanning records...");
            rset.next();
            usernameIdentification = rset.getString("username");

            if(username.equals(usernameIdentification)){
                System.out.println("User found: " + username + " is located @" + usernameIdentification
                        + " verifying password confirmation.");

                strSelect = "SELECT username, password FROM blackjackdb.authentication WHERE username = '" + username + "'";
                System.out.println(strSelect);
                rset = stmt.executeQuery(strSelect);
                rset.next();
                passwordIdentification = rset.getString("password");
                if(password.equals(passwordIdentification)){
                    System.out.println("System Login Successful.");
                }
                else{
                    System.out.println("Password Incorrect.");
                }
            }
            else{
                System.out.println("User not found!");
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {

    }


}
