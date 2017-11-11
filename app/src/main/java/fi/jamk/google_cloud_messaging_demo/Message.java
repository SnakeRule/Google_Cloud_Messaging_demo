package fi.jamk.google_cloud_messaging_demo;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Jere on 11.11.2017.
 */

public class Message {
        public String message;
        public String author;
        public String topic;
        public String token;

        public Message() {
            // Default constructor required for calls to DataSnapshot.getValue(Message.class)
        }

        public Message(String message, String author, String topic) {
            this.message = message;
            this.author = author;
            this.topic = topic;
            token = FirebaseInstanceId.getInstance().getToken();
        }
}
