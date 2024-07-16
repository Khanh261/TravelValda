package com.example.travelvalda.models;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtil {
    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_UID = "uid";

    // Lưu userId vào SharedPreferences
    public static void saveUserId(Context context, String userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_UID, userId);
        editor.apply();
    }

    // Lấy userId từ SharedPreferences
    public static String getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_UID, "");
    }
}

