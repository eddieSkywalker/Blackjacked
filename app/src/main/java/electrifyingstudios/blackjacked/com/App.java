package electrifyingstudios.blackjacked.com;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();

        Parse.initialize(App.this, "hTo2psiiLlaajve27ma7wbgn607uoHmRTmcd8ZCt", "EXmXWKhLtAKguzt077w97QCtIbPmw1rfm9awVE0Q"); // Your Application ID and Client Key are defined elsewhere

        ParseInstallation.getCurrentInstallation().saveInBackground();
//        ParsePush.subscribeInBackground("", new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
//                } else {
//                    Log.e("com.parse.push", "failed to subscribe for push", e);
//                }
//            }
//        });
    }
}

//TEST