package com.alamkanak.weekview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.alamkanak.weekview.sample.BasicActivity;

/**
 * Created by Matt on 7/13/2017.
 */


public class verifyEventDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_event_details);
        TextView textView;

        Intent intent = getIntent();
        final String StartHour = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE0);
        final String EndHour = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE1);
        final String StartMinute = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE2);
        final String EndMinute = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE3);
        final String Title = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE4);
        final String Priority = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE5);
        final String Location = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE6);
        final String StartMonth = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE7);
        final String EndMonth = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE8);
        final String StartDay = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE9);
        final String EndDay = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE10);

        textView = (TextView) findViewById(R.id.v_title);
        textView.setText(Title);

        textView = (TextView) findViewById(R.id.v_startTime);
        String startTime = StartHour + ":" + StartMinute;
        textView.setText(startTime);

        textView = (TextView) findViewById(R.id.v_startDate);
        String startDate = StartMonth + "/" + StartDay;
        textView.setText(startDate);

        textView = (TextView) findViewById(R.id.v_endDate);
        String endDate = EndMonth + "/" + EndDay;
        textView.setText(endDate);

        textView = (TextView) findViewById(R.id.v_EndTime);
        String endTime = EndHour + ":" + EndMinute;
        textView.setText(endTime);

        textView = (TextView) findViewById(R.id.v_priority);
        textView.setText(Priority);

        textView = (TextView) findViewById(R.id.v_location);
        textView.setText(Location);

        final Button button5 = (Button) findViewById(R.id.button6);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(StartHour));
                startTime.set(Calendar.MINUTE, Integer.parseInt(StartMinute));
                startTime.set(Calendar.MONTH, Integer.parseInt(StartMonth));
                startTime.set(Calendar.YEAR, 2017);
                Calendar endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR, Integer.parseInt(EndHour));
                endTime.set(Calendar.MONTH, Integer.parseInt(EndMonth));
                WeekViewEvent event = new WeekViewEvent(1, Title, startTime, endTime, "");
                event.setColor(getResources().getColor(R.color.event_color_01));

                setContentView(R.layout.activity_verify_event_details);
                WeekView mWeekView = (WeekView) findViewById(R.id.weekView);
                List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
                events.add(event);
                mWeekView.notifyDatasetChanged();
                finish();
            }
        });
    }
}
