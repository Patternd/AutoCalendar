package com.alamkanak.weekview.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//import com.alamkanak.weekview.R;

public class ViewEvent extends AppCompatActivity {

   // @Override
   // protected void onCreate(Bundle savedInstanceState) {
      //  super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_view_event);

      //  Intent intent = getIntent();
      //  String event_Information = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE);

      //  TextView textView;
      //  textView = (TextView) findViewById(R.id.textView1);
      //  textView.setText(event_Information);
   // }
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_view_event);

          Intent intent = getIntent();
          String Title = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE0);
          String StartTime = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE1);
          String EndTime = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE2);
          String Location = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE3);
          String Date = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE4);
          String Description = intent.getStringExtra(BaseActivity.EXTRA_MESSAGE5);


          TextView textView = (TextView) findViewById(R.id.title);
          textView.setText(Title);

          textView = (TextView) findViewById(R.id.startText);
          textView.setText(StartTime);

          textView = (TextView) findViewById(R.id.endText);
          textView.setText(EndTime);

          textView = (TextView) findViewById(R.id.locView);
          textView.setText(Location);

          textView = (TextView) findViewById(R.id.textView4);
          textView.setText(Date);

          textView = (TextView) findViewById(R.id.descripText);
          textView.setText(Description);

      }
}
