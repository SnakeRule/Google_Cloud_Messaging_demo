package fi.jamk.google_cloud_messaging_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Pääsivu. Näyttää laitteen token -koodin jolla firebase tunnistaa laitteen
 */

public class MainActivity extends AppCompatActivity {

    TextView tokenTextView;
    public static String userName = "anon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("asd");
        tokenTextView = (TextView) findViewById(R.id.tokenTextView);
        tokenTextView.setText("Your device token is: \n" + FirebaseInstanceId.getInstance().getToken());
    }

    public void fireBaseTopicsClicked(View v){
        Intent intent = new Intent(this, FireBaseMessagingTopics.class);
        startActivity(intent);
    }
}