package electrifyingstudios.blackjacked.com;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseObject;


public class MainActivity extends AppCompatActivity {
    public final static String DEBUG_NEWGAME = "DEBUG_TAG NewGame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        /** Called when the user clicks the Login button */
//        Button loginButton = (Button) findViewById(R.id.loginButton);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }
//        });


//        TToolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks Log In to open new activity */
    public void login_activated(View view){
        Intent openActivity = new Intent(this, LoginActivity.class);
        startActivity(openActivity);
    }

    /** Called when the user clicks New Game to open new activity */
    public void newGame_activated(View view){
        Log.d(DEBUG_NEWGAME, "new game activity activated ");
        Intent openActivity = new Intent(this, NewGameActivity.class);
        startActivity(openActivity);
    }


}
