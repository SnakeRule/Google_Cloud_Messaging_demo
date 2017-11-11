package fi.jamk.google_cloud_messaging_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jere on 11.11.2017.
 */

public class MessageListAdapter extends ArrayAdapter<String> {
    // application context
    private Context context;
    // phone data (names)
    private ArrayList<String> authors;

    private ArrayList<String> messages;

    // get application context and phones data to adapter
    public MessageListAdapter(Context context, ArrayList<String> author, ArrayList<String> message) {
        super(context, 0, author);
        this.context = context;
        authors = author;
        messages = message;
    }

    // populate every row in ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get row
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.message_layout, parent, false);

        TextView authorTextView = (TextView) rowView.findViewById(R.id.authorTextView);
        authorTextView.setText(authors.get(position));

        TextView messageTextView = (TextView) rowView.findViewById(R.id.messageTextView);
        messageTextView.setText(messages.get(position));
        // return row view
        return rowView;
    }
}
