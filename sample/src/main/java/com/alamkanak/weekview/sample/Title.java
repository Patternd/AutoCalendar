package com.alamkanak.weekview.sample;
import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alamkanak.weekview.sample.R;

public class Title extends AppCompatActivity {


    // <Matt> Declare identifier string. This is used as a flag
    // that the app will see. The BaseActivity will see the string
    // and assign the value associated with the flag accordingly.
    // Much global variables
    private eventData event_data;
    public static String title_identifier = "Title";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        Intent i = getIntent();
        event_data = (eventData) i.getSerializableExtra("eventData");



        final Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveTitle(v);
            }
        });
    }

    public void saveTitle(View view) {
        EditText editText = (EditText) findViewById(R.id.editText2);
        event_data.title = editText.getText().toString();
        Intent resultIntent;
        resultIntent = new Intent();
        Intent intent;
        intent = new Intent(this, EndTime.class);
        resultIntent.putExtra(title_identifier, event_data.title);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
