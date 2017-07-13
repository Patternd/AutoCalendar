package com.alamkanak.weekview.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class addNewEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_entry);

        // Creates an OnClick listener to NEW EVENT button
        // Calls addNewEvent() when button is clicked
        final Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNewEvent(v);
            }
        });
    }

    // Take the user to the addEvent view
    public void addNewEvent(View view) {
        Intent intent = new Intent(this, addEvent.class);
        startActivity(intent);
    }
}
