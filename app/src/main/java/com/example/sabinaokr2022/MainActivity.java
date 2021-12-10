package com.example.sabinaokr2022;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Application;
import android.content.res.Resources;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.braze.Braze;
import com.braze.BrazeUser;

import java.util.UUID;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText userEmail;
    private Button emailButton;
    private Context context;
    private TextView tvUser;
    private EditText cEvent;
    private Button cEventButton;
    private EditText cAttr;
    private Button cAttrButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Declare variables to store and id from the app design*/
        context = this.getApplicationContext();
        userEmail = findViewById(R.id.set_email);
        emailButton = findViewById(R.id.email_submit_bt);
        tvUser = findViewById(R.id.current_user);
        cEvent = findViewById(R.id.custom_eventlog);
        cEventButton = findViewById(R.id.log_event_bt);
        cAttr = findViewById(R.id.custom_attrlog);
        cAttrButton = findViewById(R.id.custom_attr_bt);

        //this is to get the current user of the session
        BrazeUser currUser = Braze.getInstance(context).getCurrentUser();
        //this is to set the current user in session in the textview
        String user_id = currUser.getUserId();
        tvUser.setText("User identified: " + user_id);

        /*This is the button to changeUser when email is set*/
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                String uEmail = userEmail.getText().toString();
                /*Creating a random UID to changeUser() after setting the Email*/
                String strID = UUID.randomUUID().toString();
                //check if empty string
                if (uEmail.length() != 0) {
                    Braze.getInstance(context).changeUser(strID);
                    Braze.getInstance(context).getCurrentUser().setEmail(uEmail);
                    Toast.makeText(context, "User Email set", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(context, "Please enter a email address.", Toast.LENGTH_LONG).show();
                }
            }
        }
        );

        //button for custom event
        cEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                String log_event = cEvent.getText().toString();
                if (log_event.length() !=0) {
                    Braze.getInstance(context).logCustomEvent(log_event);
                    Toast.makeText(context, "Logged Custom Event", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(context, "Please enter a custom event", Toast.LENGTH_LONG).show();
                }
            }
        });

        //button for custom attribute
        cAttrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String log_attr = cAttr.getText().toString();
                if (log_attr.length() != 0) {
                    Braze.getInstance(context).getCurrentUser().incrementCustomUserAttribute(log_attr,1);
                    Toast.makeText(context, "Custom Attribute +1", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(context, "No custom attribute found", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}