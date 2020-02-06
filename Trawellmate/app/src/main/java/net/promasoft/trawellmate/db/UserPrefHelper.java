package net.promasoft.trawellmate.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;


import com.google.gson.Gson;

import net.promasoft.trawellmate.argapp.UserConstantResult;
import net.promasoft.trawellmate.argapp.UserDetailsArg;

import static android.content.Context.MODE_PRIVATE;

public class UserPrefHelper {

    public static final String PREF_NAME = "TrawellmateUsrPref";
    private static UserPrefHelper ourInstance;
    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;
    private String USER_DETAILS = "UserDetails";
    private String USER_TOKEN = "Token";
    private String USER_ID = "UserId";
    private String IMAGE_URI = "ImageUri";

    private UserPrefHelper(Context context) {

        prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static UserPrefHelper getInstance(Context context) {

        if (ourInstance == null) {
            ourInstance = new UserPrefHelper(context);
        }
        return ourInstance;
    }


    public void setTokenData(String data) {
        editor.putString(USER_TOKEN, data);
        editor.commit();
    }

    public String getTokenData() {
        String data = prefs.getString(USER_TOKEN, "");
        if (data.isEmpty()) {
            return null;
        }

        return data;

    }

    public void setUserId(int data) {
        editor.putInt(USER_ID, data);
        editor.commit();
    }

    public int getUserId() {
        int data = prefs.getInt(USER_ID, 0);
        return data;

    }

    public Uri getImageUri() {
        String data = prefs.getString(IMAGE_URI, "");
        return Uri.parse(data);

    }

    public void setImageUri(Uri picUri) {
        editor.putString(IMAGE_URI, picUri.toString());
        editor.commit();
    }

    public boolean getIsImgUploaded() {
        return prefs.getBoolean("IsUploaded", false);

    }

    public void setImageUploaded(boolean uploaded) {
        editor.putBoolean("IsUploaded", uploaded);
        editor.commit();
    }

    public void setUserData(UserDetailsArg userDetailsArg) {
        editor.putString("UserDetails", new Gson().toJson(userDetailsArg));
        editor.commit();

    }

    public UserDetailsArg getUserData() {
        UserDetailsArg userDetailsArg = null;
        try {
            userDetailsArg = new Gson().fromJson(prefs.getString("UserDetails", ""), UserDetailsArg.class);
        } catch (Exception e) {
        }
        return userDetailsArg;
    }

    public void setUserConst(UserConstantResult userDetailsArg) {
        editor.putString("UserConst", new Gson().toJson(userDetailsArg));
        editor.commit();
    }

    public UserConstantResult getUserConst() {
        UserConstantResult userDetailsArg = null;
        try {
            userDetailsArg = new Gson().fromJson(prefs.getString("UserConst", ""), UserConstantResult.class);
        } catch (Exception e) {
        }
        return userDetailsArg;
    }


}
