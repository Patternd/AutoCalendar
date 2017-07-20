package com.alamkanak.weekview.sample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.alamkanak.weekview.R;

public class Location extends AppCompatActivity {

    public eventData event_data;
    public static String location_identifier = "Location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Intent i = getIntent();
        event_data = (eventData) i.getSerializableExtra("eventData");


        final Button button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveLocation(v);
            }
        });
    }

    public void saveLocation(View view) {
        EditText editText = (EditText) findViewById(R.id.editText3);
        event_data.location = editText.getText().toString();
        Intent resultIntent;
        resultIntent = new Intent();
        Intent intent;
        intent = new Intent(this, EndTime.class);
        resultIntent.putExtra(location_identifier, event_data.location);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
