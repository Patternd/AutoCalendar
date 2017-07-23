package com.alamkanak.weekview.sample;
import java.io.Serializable;
/**
 * Created by Matt on 7/18/2017.
 */

public class eventData implements Serializable {

    int startHour, endHour, startMinute, endMinute, priority, startDay, endDay, startMonth, endMonth;
    String title, description, location;
    int eventType;
    boolean deleteEvent = false;
}
