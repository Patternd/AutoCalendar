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
          String Title = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE0);
          String StartTime = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE1);
          String EndTime = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE2);
          String Location = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE3);
          String StartDate = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE4);
          String EndDate = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE6);
          String Description = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE5);


          TextView textView = (TextView) findViewById(R.id.title);
          textView.setText(Title);

          textView = (TextView) findViewById(R.id.startText);
          textView.setText(StartTime);

          textView = (TextView) findViewById(R.id.endText);
          textView.setText(EndTime);

          textView = (TextView) findViewById(R.id.locView);
          textView.setText(Location);

          textView = (TextView) findViewById(R.id.startDate);
          textView.setText(StartDate);

          textView = (TextView) findViewById(R.id.endDate);
          textView.setText(EndDate);

          textView = (TextView) findViewById(R.id.descripText);
          textView.setText(Description);

      }
}
