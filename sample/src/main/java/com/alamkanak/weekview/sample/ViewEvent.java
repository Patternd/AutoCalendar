package com.alamkanak.weekview.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//import com.alamkanak.weekview.R;

public class ViewEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        Intent intent = getIntent();
        String event_Information = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE);

        TextView textView;
        textView = (TextView) findViewById(R.id.textView1);
        textView.setText(event_Information);
    }
}
