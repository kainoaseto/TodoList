package me.kainoseto.todo.Calendar;

import com.google.api.client.util.DateTime;

import java.util.Date;

/**
 * Created by TYLER on 12/3/2016.
 */

public class CalendarEvent {
    private String title;
    private String description;
    private DateTime startDate;
    private DateTime endDate;

    public CalendarEvent(String title, String description, DateTime startDate, DateTime endDate){
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getendDate() {
        return endDate;
    }

    public void setendDate(DateTime endDate) {
        this.endDate = endDate;
    }
}