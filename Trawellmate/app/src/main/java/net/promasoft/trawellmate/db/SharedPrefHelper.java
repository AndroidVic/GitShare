package net.promasoft.trawellmate.db;

import android.content.Context;
import android.content.SharedPreferences;


import static android.content.Context.MODE_PRIVATE;

public class SharedPrefHelper {

    public static final String PREF_NAME = "TrawellmateSharedPref";
    private static SharedPrefHelper ourInstance;
    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;
    private final String IS_LOGIN = "IsLoginIn";
    private final String IS_FIRST = "IsFirstTime";
    private String USER_LANG = "UesrLang";

    private SharedPrefHelper(Context context) {

        prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static SharedPrefHelper getInstance(Context context) {

        if (ourInstance == null) {
            ourInstance = new SharedPrefHelper(context);
        }
        return ourInstance;
    }


    public boolean getIsLogin() {
        return prefs.getBoolean(IS_LOGIN, false);
    }

    public void setIsLogin(boolean isLogin) {
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();
    }


    public boolean getIsFirstTime() {
        return prefs.getBoolean(IS_FIRST, true);
    }

    public void setIsFirstTime(boolean isFirst) {
        editor.putBoolean(IS_FIRST, isFirst);
        editor.commit();
    }


}
