package fi.jamk.google_cloud_messaging_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class FireBaseMessaging_Notifications extends AppCompatActivity {

    private static final String TAG = "FireBaseMessaging";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_messaging_jere);

        //Get the token
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + token);
        Toast.makeText(FireBaseMessaging_Notifications.this, token, Toast.LENGTH_SHORT).show();
    }
}
