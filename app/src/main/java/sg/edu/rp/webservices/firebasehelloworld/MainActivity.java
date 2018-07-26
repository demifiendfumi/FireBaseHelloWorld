package sg.edu.rp.webservices.firebasehelloworld;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView tvMessage, tvMessagePriority;
    private Button btnMessage;
    private EditText etMessage, etMessagePriority;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference messagePOJOReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get UI element
        tvMessage = (TextView) findViewById(R.id.textViewMessage);
        tvMessagePriority = (TextView)findViewById(R.id.textViewMessagePriority);
        btnMessage = findViewById(R.id. buttonAdd);
        etMessage = findViewById(R.id. editTextMessageText);
        etMessagePriority = (EditText)findViewById(R.id.editTextMessagePriority);
        // TODO: Task 2 - Get Firebase database instance and reference
        // TODO: Task 2 - Get Firebase database instance and reference
        firebaseDatabase = FirebaseDatabase.getInstance();
        messagePOJOReference = firebaseDatabase.getReference("messagePOJO");

        // TODO: Task 3 - Add a value event listener to the "message" node
        messagePOJOReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // TODO: Task 4 - Get the latest value from the dataSnapshot and display on the UI using the EditText message
                // This method will get fired everytime the "message" value updates in the realtime database. We're getting our data back as a DataSnapshot
                Message message = dataSnapshot.getValue(Message.class);
                if (message != null) {
                    tvMessage.setText(message.getText());
                    tvMessagePriority.setText("Priority is " + message.getPriority());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Database error occurred", databaseError.toException());
            }


//        btnMessage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Note: We're not directly updating the text view, but calling setValue() to update the data in the database instead
//                    Message message = new Message(etMessage.getText().toString(), etMessagePriority.getText().toString());
//                    messagePOJOReference.setValue(message);
//                }
//            });
        });

        // TODO: Task 5 - Update UI elements, and then include OnClickListener for Send Message button
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message(etMessage.getText().toString(), etMessagePriority.getText().toString());
                    messagePOJOReference.setValue(message);
            }
        });
    }
}
