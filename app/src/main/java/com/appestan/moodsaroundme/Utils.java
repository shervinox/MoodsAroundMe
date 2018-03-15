package com.appestan.moodsaroundme;

import android.util.Log;
import android.widget.EditText;

/**
 * Created with love and care by shervin on 03/15/2018.
 */

public class Utils {
    private static final String TAG = "Utils";
    public static boolean validateBirthDay(Birthday birthday) {
        if (birthday.getYearDateInt() > 1900 && birthday.getYearDateInt() < 2010) {
            Log.d(TAG, "validateBirthDay: true");
            return true;
        }
        Log.d(TAG, "validateBirthDay: false");
        return false;
    }

    public static int validateYearEditText(EditText text) {
        if (text == null || text.length() != 4) {
            return 0;
        }

        return Integer.valueOf(String.valueOf(text.getText()));
    }

    public static int validateMonthEditText(EditText text) {
        if (text == null || text.length() != 2) {
            return 0;
        }

        return Integer.valueOf(String.valueOf(text.getText()));
    }

    public static int validateDayEditText(EditText text) {
        if (text == null || text.length() != 2) {
            return 0;
        }

        return Integer.valueOf((text.getText().toString()));
    }

    public static boolean validateName(EditText nameOfEditText) {
        if (nameOfEditText.getText() != null && nameOfEditText.getText().toString() != null && nameOfEditText.getText().toString().length() > 1) {
            Log.d(TAG, "validateName: true");
            return true;
        }
        Log.d(TAG, "validateName: false");
        return false;
    }

    public static String fetchName(EditText nameOfEditText) {
        return nameOfEditText.getText().toString();
    }
}
