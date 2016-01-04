package electrifyingstudios.blackjacked.com;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.AlertDialog;
import android.app.Dialog;

public class LoginActivity extends AppCompatActivity
{
    private static final String DEBUG_STARTUP = "Loading..";
    private static final String DEBUG_REGISTER = "Registering..";
    private static final String DEBUG_REGISTRATION_SUCCESS = "Register success..";
    private static final String DEBUG_REGISTRATION_FAILED = "Register failure..";
    private static final String DEBUG_LOGIN = "Logging in..";
    private static final String DEBUG_LOGIN_SUCCESS = "Login success..";
    private static final String DEBUG_LOGIN_FAILED = "Login failure..";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(DEBUG_STARTUP, "login activity activated ");

//        new AlertDialog.Builder(this)
//                .setTitle("Delete entry")
//                .setMessage("Are you sure you want to delete this entry?")
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // continue with delete
//                    }
//                })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do nothing
//                    }
//                })
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show();
//
//        AlertDialog loginDialog = new AlertDialog.Builder(this).create();
//        loginDialog.setTitle("Success");
//        loginDialog.setMessage("Welcome to BlackJacked!");
//        loginDialog.setButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        loginDialog.setIcon(android.R.drawable.ic_dialog_alert);
//        loginDialog.show();

//        FragmentManager fm = getFragmentManager();
//        MyAlertDialogFragment dialogFragment = new MyAlertDialogFragment ();
//        dialogFragment.show(fm, "Sample Fragment");




//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();
//        testObject.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e != null) {
//                    Log.e("PARSE.COM","FAILED" + e.getMessage());
//                }
//                else {
//                    Log.e("PARSE.COM","SUCCESS");
//                }
//            }
//        });

        /** Called when the user clicks the Register button */
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                /** Creates local GUI object [button or text view] and pulls data from according Activity XML file, and transfers to preferred variables if necessary */
                EditText editTextEmail = (EditText) findViewById(R.id.email);
                EditText editTextPassword = (EditText) findViewById(R.id.password);
                String username = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                final TextView duplicateUserWarning = (TextView) findViewById(R.id.duplicateUserWarning);

                /** new user creation */
                Log.d(DEBUG_REGISTER, "register method accessed | login activity activated ");
                ParseUser newUser = new ParseUser();
                newUser.setUsername(username);
                newUser.setPassword(password);

                newUser.signUpInBackground(new SignUpCallback()
                {
                    public void done(ParseException e)
                    {
                        if (e == null) {
                            /** successful registration, alert user and close Login Activity. */
                            Log.d(DEBUG_REGISTRATION_SUCCESS, "user registration successful | register method accessed | login activity activated ");
                            duplicateUserWarning.setVisibility(View.INVISIBLE);
                            duplicateUserWarning.setTextColor(0x00BB00);
                            duplicateUserWarning.setText("Logged in!");
                            FragmentManager fm = getFragmentManager();
                            MyAlertDialogFragment dialogFragment = new MyAlertDialogFragment ();
                            dialogFragment.show(fm, "Success.");

                            kill_activity();
                        }
                        else {
                            /** sign up failed, check parse exception to see why. */
                            Log.d(DEBUG_REGISTRATION_FAILED, "user registration failure | register method accessed | login activity activated ");
                            duplicateUserWarning.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });


        /** Called when the user clicks the Login button */
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /** Creates local GUI object [button or text view] and pulls data from according Activity XML file, and transfers to preferred variables if necessary */
                EditText editTextEmail = (EditText) findViewById(R.id.email);
                EditText editTextPassword = (EditText) findViewById(R.id.password);
                String username = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                final TextView duplicateUserWarning = (TextView) findViewById(R.id.duplicateUserWarning);

                /** authenticate user login */
                Log.d(DEBUG_LOGIN, "login method accessed | login activity activated ");
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            /** successful login, alert user and close Login Activity. */
                            Log.d(DEBUG_LOGIN_SUCCESS, "user login successful | register method accessed | login activity activated ");
                            duplicateUserWarning.setVisibility(View.INVISIBLE);
                            duplicateUserWarning.setTextColor(0x00BB00);
                            duplicateUserWarning.setText("Logged in!");
                            FragmentManager fm = getFragmentManager();
                            MyAlertDialogFragment dialogFragment = new MyAlertDialogFragment();
                            dialogFragment.show(fm, "Success.");

                            kill_activity();
                        } else {
                            /** sign up failed, check parse exception to see why. */
                            Log.d(DEBUG_LOGIN_FAILED, "user login failure | register method accessed | login activity activated ");
                            duplicateUserWarning.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });

    } // Activity OnCreate End Bracket

    void kill_activity()
    {
        finish();
    }

}// Activity End Bracket

