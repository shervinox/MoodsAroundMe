package com.appestan.moodsaroundme;

/**
 * Created with love and care by shervin on 03/15/2018.
 */

public class Buddy {

    Birthday birthday;
    String name;


    public String getName() {
        return name;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public Buddy(Birthday birthday, String name) {
        this.birthday = birthday;
        this.name = name;
    }
}
