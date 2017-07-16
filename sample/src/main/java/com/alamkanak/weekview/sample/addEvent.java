package com.alamkanak.weekview.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class addEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        final Button button4 = (Button) findViewById(R.id.button4);

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addOneTimeEvent(v);
            }
        });
    }

    public void addOneTimeEvent(View view) {
        Intent intent = new Intent(this, addOneTimeEvent.class);
        startActivity(intent);
    }

}
