package com.alamkanak.weekview.sample;
import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.alamkanak.weekview.sample.R;

import static com.alamkanak.weekview.sample.R.id.timePicker;

public class EndTime extends AppCompatActivity {

    private eventData event_data;
    public static String end_hour_identifier = "EndHour";
    public static String end_minute_identifier = "EndMinute";
    private TimePicker timePicker1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_time);

        Intent i = getIntent();
        event_data = (eventData) i.getSerializableExtra("eventData");
        timePicker1 = (TimePicker) findViewById(R.id.timePicker);


        final Button button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveEndTime(v);
            }
        });
    }

    public void saveEndTime(View view) {

        int h = timePicker1.getCurrentHour();
        int m = timePicker1.getCurrentMinute();
        Intent resultIntent;
        resultIntent = new Intent();

        resultIntent.putExtra(end_hour_identifier, h);
        resultIntent.putExtra(end_minute_identifier, m);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
