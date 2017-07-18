package com.alamkanak.weekview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by Matt on 7/13/2017.
 */

public class addOneTimeEvent extends AppCompatActivity {

    eventData event_Data;
    EditText editText;
    public static final String EXTRA_MESSAGE0 = "0";
    public static final String EXTRA_MESSAGE1 = "1";
    public static final String EXTRA_MESSAGE2 = "2";
    public static final String EXTRA_MESSAGE3 = "3";
    public static final String EXTRA_MESSAGE4 = "4";
    public static final String EXTRA_MESSAGE5 = "5";
    public static final String EXTRA_MESSAGE6 = "6";
    public static final String EXTRA_MESSAGE7 = "7";
    public static final String EXTRA_MESSAGE8 = "8";
    public static final String EXTRA_MESSAGE9 = "9";
    public static final String EXTRA_MESSAGE10 = "10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_one_time_event);


        final Button button5 = (Button) findViewById(R.id.button5);
        getIntent().getSerializableExtra("eventData");
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setNewEventDetails(v);
            }
        });
    }

    public void setNewEventDetails(View view) {

        Intent intent = new Intent(this, verifyEventDetails.class);
        editText = (EditText) findViewById(R.id.Start_Hour);
        String event_StartHour = editText.getText().toString();

        editText = (EditText) findViewById(R.id.Start_Minute);
        String event_StartMinute = editText.getText().toString();

        editText = (EditText) findViewById(R.id.End_Hour);
        String event_EndHour = editText.getText().toString();

        editText = (EditText) findViewById(R.id.End_Minute);
        String event_EndMinute = editText.getText().toString();

        editText = (EditText) findViewById(R.id.Event_Title);
        String event_Title = editText.getText().toString();

        editText = (EditText) findViewById(R.id.Priority);
        String event_Priority = editText.getText().toString();

        editText = (EditText) findViewById(R.id.Location);
        String event_Location = editText.getText().toString();

        editText = (EditText) findViewById(R.id.Start_Month);
        String event_StartMonth = editText.getText().toString();

        editText = (EditText) findViewById(R.id.End_Month);
        String event_EndMonth = editText.getText().toString();

        editText = (EditText) findViewById(R.id.start_Day);
        String event_StartDay = editText.getText().toString();

        editText = (EditText) findViewById(R.id.End_Day);
        String event_EndDay = editText.getText().toString();

        intent.putExtra(EXTRA_MESSAGE0,event_StartHour);
        intent.putExtra(EXTRA_MESSAGE1,event_EndHour);
        intent.putExtra(EXTRA_MESSAGE2,event_StartMinute);
        intent.putExtra(EXTRA_MESSAGE3,event_EndMinute);
        intent.putExtra(EXTRA_MESSAGE4,event_Title);
        intent.putExtra(EXTRA_MESSAGE5,event_Priority);
        intent.putExtra(EXTRA_MESSAGE6,event_Location);
        intent.putExtra(EXTRA_MESSAGE7,event_StartMonth);
        intent.putExtra(EXTRA_MESSAGE8,event_EndMonth);
        intent.putExtra(EXTRA_MESSAGE9,event_StartDay);
        intent.putExtra(EXTRA_MESSAGE10,event_EndDay);


        startActivity(intent);

    }
}
