package fi.jamk.google_cloud_messaging_demo;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class FireBaseMessagingTopics extends AppCompatActivity {
    TextView name;
    EditText subscribeEditText;
    String sub;
    Context context;
    String response;
    RequestQueue MyRequestQueue;
    FirebaseDatabase database;
    EditText titleEditText;
    EditText authorEditText;
    EditText topicEditText;
    DatabaseReference dbRef;
    ListView listView;
    ArrayList<String> authors = new ArrayList<>();
    ArrayList<String> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_messaging_chat_room);
        titleEditText = (EditText)findViewById(R.id.et_title);
        topicEditText = (EditText)findViewById(R.id.topicEditText);
        listView = (ListView) findViewById(R.id.messageListView);
        database = FirebaseDatabase.getInstance();
        name = (TextView) findViewById(R.id.nameTextView);
        UpdateName();

        dbRef = database.getReference();
        dbRef.child("messages").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        authors.clear();
                        messages.clear();

                        for(DataSnapshot snap : dataSnapshot.getChildren()) {
                            Message message = snap.getValue(Message.class);
                            if(snap.child("message").getValue() != null && snap.child("author").getValue() != null) {
                                authors.add(snap.child("author").getValue().toString());
                                messages.add(snap.child("message").getValue().toString());
                            }
                    }
                        LoadList();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topic_chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_changeName:
                ChangeName();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void subscribePressed(View v)
    {
        DatabaseReference myRef = database.getReference("messages").push();
        Message message = new Message(titleEditText.getText().toString(),
                MainActivity.userName, topicEditText.getText().toString());
        myRef.setValue(message);
    }

    void LoadList()
    {
        Collections.reverse(authors);
        Collections.reverse(messages);
        MessageListAdapter adapter = new MessageListAdapter(getApplicationContext(), authors, messages);
        listView.setAdapter(adapter);
    }

    public void ChangeName(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText input = new EditText(this);
        input.setText(MainActivity.userName);
        builder.setTitle("Set username");
        builder.setView(input);

        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MainActivity.userName = input.getText().toString();
                UpdateName();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void UpdateName()
    {
        name.setText("Username: " + MainActivity.userName);
    }
}
