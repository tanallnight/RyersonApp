package com.prototype.ryersonapp;

public class Reminder {

    int _id;
    String _title;
    String _description;
    String _day;
    String _month;
    String _year;
    String _hour;
    String _minute;

    public Reminder() {
    }

    public Reminder(String title, String description, String day, String month, String year, String hour, String minute) {
        _title = title;
        _description = description;
        _day = day;
        _month = month;
        _year = year;
        _hour = hour;
        _minute = minute;
    }

    public Reminder(int id, String title, String description, String day, String month, String year, String hour, String minute) {
        _id = id;
        _title = title;
        _description = description;
        _day = day;
        _month = month;
        _year = year;
        _hour = hour;
        _minute = minute;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_day() {
        return _day;
    }

    public void set_day(String _day) {
        this._day = _day;
    }

    public String get_month() {
        return _month;
    }

    public void set_month(String _month) {
        this._month = _month;
    }

    public String get_year() {
        return _year;
    }

    public void set_year(String _year) {
        this._year = _year;
    }

    public String get_hour() {
        return _hour;
    }

    public void set_hour(String _hour) {
        this._hour = _hour;
    }

    public String get_minute() {
        return _minute;
    }

    public void set_minute(String _minute) {
        this._minute = _minute;
    }
}
