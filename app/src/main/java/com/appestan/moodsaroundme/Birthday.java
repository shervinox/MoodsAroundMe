package com.appestan.moodsaroundme;

/**
 * Created with love and care by shervin on 03/15/2018.
 */

public class Birthday {

    @Override
    public String toString() {
        return this.getYearDateInt() + "/" + this.getMonthDateInt() + "/" + this.getMonthDateInt();
    }

    int yearDateInt;
    int monthDateInt;
    int dayDateInt;

    public Birthday() {
    }

    public int getYearDateInt() {
        return yearDateInt;
    }

    public void setYearDateInt(int yearDateInt) {
        this.yearDateInt = yearDateInt;
    }

    public int getMonthDateInt() {
        return monthDateInt;
    }

    public void setMonthDateInt(int monthDateInt) {
        this.monthDateInt = monthDateInt;
    }

    public int getDayDateInt() {
        return dayDateInt;
    }

    public void setDayDateInt(int dayDateInt) {
        this.dayDateInt = dayDateInt;
    }

}
