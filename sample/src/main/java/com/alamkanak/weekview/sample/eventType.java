package com.alamkanak.weekview.sample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.alamkanak.weekview.R;

public class eventType extends AppCompatActivity {


    // <Matt> Declare identifier string. This is used as a flag
    // that the app will see. The BaseActivity will see the string
    // and assign the value associated with the flag accordingly.
    // Much global variables
    private eventData event_data;
    public static String eventType_identifier = "eventType";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type);
        Intent i = getIntent();
        event_data = (eventData) i.getSerializableExtra("eventData");
        final Button button2 = (Button) findViewById(com.alamkanak.weekview.sample.R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveEventType(v);
            }
        });

        final Button button = (Button) findViewById(com.alamkanak.weekview.sample.R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                event_data.eventType = 0;
                Intent resultIntent;
                resultIntent = new Intent();
                resultIntent.putExtra(eventType_identifier, event_data.eventType);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    public void saveEventType(View view) {
        event_data.eventType = 1;
        Intent resultIntent;
        resultIntent = new Intent();
        Intent intent;
        intent = new Intent(this, Title.class);
        resultIntent.putExtra(eventType_identifier, event_data.eventType);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
