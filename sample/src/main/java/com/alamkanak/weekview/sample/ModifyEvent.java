package com.alamkanak.weekview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by Alex Glahn on 7/13/2017.
 */
    public class ModifyEvent extends AppCompatActivity {

    private eventData event_data;
    public static String deleteEvent_identifier = "Delete";
    private void editEvent(View v){
        Intent intent = new Intent(this, EditEvent.class);

        startActivity(intent);
    }

    private void deleteEvent(View v) {
        event_data.deleteEvent = true;
        Intent intent;
        intent = new Intent(this, EndTime.class);
        intent.putExtra(deleteEvent_identifier, event_data.deleteEvent);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_modify_event);

            Intent i = getIntent();
            event_data = (eventData) i.getSerializableExtra("eventData");

            final Button edit = (Button) findViewById(R.id.button_edit);
            edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    editEvent(v);
                }
            });

            final Button delete = (Button) findViewById(R.id.button_delete);
            delete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    deleteEvent(v);
                }
            });
        }
}
