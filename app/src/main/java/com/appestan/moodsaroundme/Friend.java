package com.appestan.moodsaroundme;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class Friend {

    public Friend() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String day_;
    private String month_;

    public String getDay_() {
        return day_;
    }

    public void setDay_(String day_) {
        this.day_ = day_;
    }

    public String getMonth_() {
        return month_;
    }

    public void setMonth_(String month_) {
        this.month_ = month_;
    }

    public String getYear_() {
        return year_;
    }

    public void setYear_(String year_) {
        this.year_ = year_;
    }

    private String year_;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private Date birthday;

    public int calculateCycle1() {
        double res = internalCalculate(23);
        return (int) (res * 100);
    }

    public int calculateCycle2() {
        double res = internalCalculate(28);
        return (int) (res * 100);
    }

    public int calculateCycle3() {

        double res = internalCalculate(33);
        return (int) (res * 100);
    }

    private double internalCalculate(int days) {
        int diff = getDaysFromBirthday();
        double result = Math.sin(diff * 2 * Math.PI / days);
        return result;
    }

    private int getDaysFromBirthday() {
        Date currentDate = Calendar.getInstance().getTime();
        long diff = currentDate.getTime() - birthday.getTime();
        double days = Math.floor(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        return ((int) days);
    }
}
