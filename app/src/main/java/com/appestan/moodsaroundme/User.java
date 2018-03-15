package com.appestan.moodsaroundme;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created with love and care by shervin on 03/15/2018.
 */

@DatabaseTable(tableName = User.TABLE_NAME_USERS, daoClass = Buddy.class)
public class User {

    public static final String TABLE_NAME_USERS = "users";

    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_NAME = "name";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String mName;

    public User() {
        // Don't forget the empty constructor, needed by ORMLite.
    }

    /**
     * Getters & Setters
     **/

//    ...
    public String getName() {
        return mName;
    }

    public void setName(String nameStr) {
        mName = nameStr;
    }

}

