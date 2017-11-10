package fi.jamk.google_cloud_messaging_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fireBaseMessagingClicked(View v){
        Intent intent = new Intent(this, FireBaseMessaging_Notifications.class);
        startActivity(intent);
    }
}