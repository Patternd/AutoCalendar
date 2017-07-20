package com.alamkanak.weekview.sample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.alamkanak.weekview.R;

public class Description extends AppCompatActivity {


    public eventData event_data;
    public static String description_identifier = "Description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Intent i = getIntent();
        event_data = (eventData) i.getSerializableExtra("eventData");

        final Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDescription(v);
            }
        });
    }

    public void saveDescription(View view) {
        EditText editText = (EditText) findViewById(R.id.editText4);
        event_data.description = editText.getText().toString();
        Intent resultIntent;
        resultIntent = new Intent();
        Intent intent;
        intent = new Intent(this, EndTime.class);
        resultIntent.putExtra(description_identifier, event_data.description);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
