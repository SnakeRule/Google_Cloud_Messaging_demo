package fi.jamk.google_cloud_messaging_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    public static String userName = "anon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("asd");
    }

    public void fireBaseMessagingClicked(View v){
        Intent intent = new Intent(this, FireBaseMessaging_Notifications.class);
        startActivity(intent);
    }

    public void fireBaseTopicsClicked(View v){
        Intent intent = new Intent(this, FireBaseMessagingTopics.class);
        startActivity(intent);
    }
}