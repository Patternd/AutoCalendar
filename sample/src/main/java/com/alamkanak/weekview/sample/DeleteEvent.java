package com.alamkanak.weekview.sample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Alex Glahn on 7/13/2017.
 */
public class DeleteEvent extends AppCompatActivity {

    private eventData event_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_event);
        Intent i = getIntent();
        event_data = (eventData) i.getSerializableExtra("eventData");

        final Button yes = (Button) findViewById(R.id.button);
        yes.setOnClickListener(new View.OnClickListener() {
            @TargetApi(16)
            public void onClick(View v) {
                //Code for deleting event
                //mNewEvents.remove(event);
                //mWeekView.notifyDatasetChanged();
                //return to home screen

                Intent intent = new Intent(getApplicationContext(), BasicActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        final Button no = (Button) findViewById(R.id.button2);
        no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("None", event_data.title);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}