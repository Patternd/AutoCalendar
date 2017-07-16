package com.alamkanak.weekview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Matt on 7/13/2017.
 */

public class verifyEventDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_event_details);

        Intent intent = getIntent();
        String StartHour = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE0);
        String EndHour = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE1);
        String StartMinute = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE2);
        String EndMinute = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE3);
        String Title = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE4);
        String Priority = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE5);
        String Location = intent.getStringExtra(addOneTimeEvent.EXTRA_MESSAGE6);

    }
}
