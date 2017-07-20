package com.alamkanak.weekview.sample;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import static com.alamkanak.weekview.sample.Description.description_identifier;
import static com.alamkanak.weekview.sample.EndTime.end_hour_identifier;
import static com.alamkanak.weekview.sample.EndTime.end_minute_identifier;
import static com.alamkanak.weekview.sample.Location.location_identifier;
import static com.alamkanak.weekview.sample.Title.title_identifier;
import static com.alamkanak.weekview.sample.eventType.eventType_identifier;
import static java.lang.String.valueOf;


public abstract class BaseActivity extends AppCompatActivity implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {
    public static final String EXTRA_MESSAGE0 = "0";
    public static final String EXTRA_MESSAGE1 = "1";
    public static final String EXTRA_MESSAGE2 = "2";
    public static final String EXTRA_MESSAGE3 = "3";
    public static final String EXTRA_MESSAGE4 = "4";
    public static final String EXTRA_MESSAGE5 = "5";
    public static final String EXTRA_MESSAGE6 = "6";

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;
    private ArrayList<WeekViewEvent> mNewEvents;
    private eventData event_data = new eventData();
    Calendar GlobalTime;
    WeekViewEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this);

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);

        // Initially, there will be no events on the week view because the user has not tapped on
        // it yet.
        mNewEvents = new ArrayList<WeekViewEvent>();
    }

    // <Matt> In order to repopulate the calendar with added events, this function is used
    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with the events that was added by tapping on empty view.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        ArrayList<WeekViewEvent> newEvents = getNewEvents(newYear, newMonth);
        events.addAll(newEvents);
        return events;
    }

    // <Matt> Setting the eventVariable of the event. This could be title, description, priority, etc
    //
    public void setEntryVariable(int param) {
        Intent intent;
        switch (param) {
            case 0:
                intent = new Intent(this, Title.class);
                intent.putExtra("eventData", (Serializable) event_data);
                startActivityForResult(intent,0);
                break;
            case 1:
                intent = new Intent(this, EndTime.class);
                intent.putExtra("eventData", (Serializable) event_data);
                startActivityForResult(intent, 1);
                break;
            case 2:
                intent = new Intent(this, eventType.class);
                intent.putExtra("eventData", (Serializable) event_data);
                startActivityForResult(intent,2);
                break;
            case 3:
                intent = new Intent(this, Location.class);
                intent.putExtra("eventData", (Serializable) event_data);
                startActivityForResult(intent,3);
                break;
            case 4:
                intent = new Intent(this, Description.class);
                intent.putExtra("eventData", (Serializable) event_data);
                startActivityForResult(intent,4);
                break;
        }
    }


    public void modifyEvent(WeekViewEvent event) {

        Intent intent = new Intent(this, ModifyEvent.class);


        String event_Info = event.getName();
        startActivity(intent);
    }


    public void setEventColor() {
        Random rand = new Random();

        int  n = rand.nextInt(4) + 1;

        switch (n) {
            case (1):
                event.setColor(getResources().getColor(R.color.event_color_01));
                break;
            case (2):
                event.setColor(getResources().getColor(R.color.event_color_02));
                break;
            case (3):
                event.setColor(getResources().getColor(R.color.event_color_03));
                break;
            case (4):
                event.setColor(getResources().getColor(R.color.event_color_04));
                break;
        }
    }

    // <Matt> This is needed to return a variable from a subActivity. Refer to this link
    //  https://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (0) : {
                if (resultCode == Activity.RESULT_OK) {
                    String newText = data.getStringExtra(title_identifier);
                    event_data.title = newText;
                    setEntryVariable(1);
                }
                break;
            }
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {
                    int Hour = data.getIntExtra(end_hour_identifier, 0);
                    int Minute = data.getIntExtra(end_minute_identifier, 0);
                    event_data.endHour = Hour;
                    event_data.endMinute = Minute;
                    setEntryVariable(3);
                }
                break;
            }
            case (2) : {
                if (resultCode == Activity.RESULT_OK) {
                    int type = data.getIntExtra(eventType_identifier, 0);
                    event_data.eventType = type;

                    if (type == 0) {
                        //call the task activity series
                        break;
                    }
                    else if (type == 1) {
                        setEntryVariable(0);
                    }
                }
                break;
            }

            case (3) : {
                if (resultCode == Activity.RESULT_OK) {

                    String location = data.getStringExtra(location_identifier);
                    event_data.location = location;
                    setEntryVariable(4);
                    break;
                }
                break;
            }

            case (4) : {
                if (resultCode == Activity.RESULT_OK) {
                    String description = data.getStringExtra(description_identifier);
                    event_data.description = description;
                }
            }
        }
        if (requestCode == 4) {
            //Calendar calendar = Calendar.getInstance();
            int AM_PM = GlobalTime.get(Calendar.AM_PM);
            if (AM_PM == 1) {
                GlobalTime.set(Calendar.AM_PM, 0);
                GlobalTime.set(Calendar.HOUR, event_data.startHour);
            }

            Calendar endTime = (Calendar) GlobalTime.clone();
            endTime.set(Calendar.HOUR, event_data.endHour);
            endTime.set(Calendar.HOUR_OF_DAY, event_data.endHour);
            endTime.set(Calendar.MINUTE, event_data.endMinute);
            //endTime.set(Calendar.DAY_OF_MONTH, calendar.DAY_OF_MONTH);
            //endTime.set(Calendar.MONTH, calendar.MONTH);

            // Create a new event.
            event = new WeekViewEvent(20, event_data.title, GlobalTime, endTime, event_data.description);
            setEventColor();
            event.setLocation(event_data.location);
            mNewEvents.add(event);

            // Refresh the week view. onMonthChange will be called again.
            mWeekView.notifyDatasetChanged();
        }
    }

    /**
     * Get events that were added by tapping on empty view.
     * @param year The year currently visible on the week view.
     * @param month The month currently visible on the week view.
     * @return The events of the given year and month.
     */
    private ArrayList<WeekViewEvent> getNewEvents(int year, int month) {

        // Get the starting point and ending point of the given month. We need this to find the
        // events of the given month.
        Calendar startOfMonth = Calendar.getInstance();
        startOfMonth.set(Calendar.YEAR, year);
        startOfMonth.set(Calendar.MONTH, month - 1);
        startOfMonth.set(Calendar.DAY_OF_MONTH, 1);
        startOfMonth.set(Calendar.HOUR_OF_DAY, 0);
        startOfMonth.set(Calendar.MINUTE, 0);
        startOfMonth.set(Calendar.SECOND, 0);
        startOfMonth.set(Calendar.MILLISECOND, 0);
        Calendar endOfMonth = (Calendar) startOfMonth.clone();
        endOfMonth.set(Calendar.DAY_OF_MONTH, endOfMonth.getMaximum(Calendar.DAY_OF_MONTH));
        endOfMonth.set(Calendar.HOUR_OF_DAY, 23);
        endOfMonth.set(Calendar.MINUTE, 59);
        endOfMonth.set(Calendar.SECOND, 59);

        // Find the events that were added by tapping on empty view and that occurs in the given
        // time frame.
        ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent event : mNewEvents) {
            if (event.getEndTime().getTimeInMillis() > startOfMonth.getTimeInMillis() &&
                    event.getStartTime().getTimeInMillis() < endOfMonth.getTimeInMillis()) {
                events.add(event);
            }
        }
        return events;
    }

    // <Matthew Staudigel> 7-9-17
    // Click on an event block and the event's details will be displayed in a new window
    public void viewEvent(WeekViewEvent event) {
        Intent intent = new Intent(this, ViewEvent.class);
        EditText editText = (EditText) findViewById(R.id.editText);

        String Title = event.getName();
        String tmpMinute = "";
        String tmp1Minute = "";
        String StartTime = "";
        String EndTime = "";

        if(event_data.startMinute <= 9 && event_data.startHour < 12){
            tmpMinute = "0" + event_data.startMinute;
            StartTime = valueOf(event_data.startHour) + ":" + valueOf(tmpMinute) + " AM";
        }
        else if(event_data.startMinute <= 9 && event_data.startHour > 12){
            tmpMinute = "0" + event_data.startMinute;
            StartTime = valueOf(event_data.startHour - 12) + ":" + valueOf(tmpMinute) + " PM";
        }
        else if(event_data.startHour > 12) {
            StartTime = valueOf((event_data.startHour - 12) + ":" + valueOf(event_data.startMinute) + " PM");
        }
        else {
            StartTime = valueOf(event_data.startHour) + ":" + valueOf(event_data.startMinute) + " AM";
        }

        if(event_data.endMinute <= 9 && event_data.endHour < 12){
            tmp1Minute = "0" + event_data.endMinute;
            EndTime = valueOf(event_data.endHour) + ":" + valueOf(tmp1Minute) + " AM";
        }
        else if(event_data.endMinute <= 9 && event_data.endHour > 12){
            tmp1Minute = "0" + event_data.endMinute;
            EndTime = valueOf(event_data.endHour - 12) + ":" + valueOf(tmp1Minute) + " PM";
        }
        else if(event_data.endHour > 12) {
            EndTime = valueOf((event_data.endHour - 12) + ":" + valueOf(event_data.endMinute) + " PM");
        }
        else {
            EndTime = valueOf(event_data.endHour) + ":" + valueOf(event_data.endMinute) + " AM";
        }




        String Location = event.getLocation();
        String StartDate = valueOf(event_data.startMonth+1) + "/" + valueOf(event_data.startDay);
        String EndDate = valueOf(event_data.endMonth+1) + "/" + valueOf(event_data.endDay);
        String Description = event.getDescription().toString();

        intent.putExtra(EXTRA_MESSAGE0, Title);
        intent.putExtra(EXTRA_MESSAGE1, StartTime);
        intent.putExtra(EXTRA_MESSAGE2, EndTime);
        intent.putExtra(EXTRA_MESSAGE3, Location);
        intent.putExtra(EXTRA_MESSAGE4, StartDate);
        intent.putExtra(EXTRA_MESSAGE6, EndDate);
        intent.putExtra(EXTRA_MESSAGE5, Description);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_week_view);
        switch (id){
            case R.id.action_today:
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                if (hour == 12) {
                    return "12 PM";
                }
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        viewEvent(event);
        Toast.makeText(this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        modifyEvent(event);
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {

        // <Matt> Initial call to first dialog. Also, set a global variable for time LMAO
        GlobalTime = time;

        int AM_PM = GlobalTime.get(Calendar.AM_PM);
        if (AM_PM == 0) {
            event_data.startHour = GlobalTime.get(Calendar.HOUR);
            event_data.startMinute = GlobalTime.get(Calendar.MINUTE);
            if (event_data.startMinute < 10) {
                Toast.makeText(this, "Chosen start time: " + event_data.startHour + ":" + "0" + event_data.startMinute + " AM", Toast.LENGTH_LONG).show();
            }

            else {
                Toast.makeText(this, "Chosen start time: " + event_data.startHour + ":" + event_data.startMinute+ " AM", Toast.LENGTH_LONG).show();
            }
        }
        else if (AM_PM == 1) {
            if (event_data.startMinute < 10) {
                Toast.makeText(this, "Chosen start time: " + GlobalTime.get(Calendar.HOUR) + ":" + "0" + GlobalTime.get(Calendar.MINUTE)+ " PM", Toast.LENGTH_LONG).show();
            }

            else {
                Toast.makeText(this, "Chosen start time: " + GlobalTime.get(Calendar.HOUR) + ":" + GlobalTime.get(Calendar.MINUTE)+ " PM", Toast.LENGTH_LONG).show();
            }
            GlobalTime.set(Calendar.AM_PM, 0);
            event_data.startHour = GlobalTime.get(Calendar.HOUR)+12;
            GlobalTime.set(Calendar.HOUR, event_data.startHour);
            event_data.startMinute = GlobalTime.get(Calendar.MINUTE);
        }

        event_data.startMonth = GlobalTime.get(Calendar.MONTH);
        event_data.endMonth = GlobalTime.get(Calendar.MONTH);

        event_data.startDay = GlobalTime.get(Calendar.DAY_OF_MONTH);
        event_data.endDay = GlobalTime.get(Calendar.DAY_OF_MONTH);

        setEntryVariable(2);

    }

    public WeekView getWeekView() {
        return mWeekView;
    }
}
