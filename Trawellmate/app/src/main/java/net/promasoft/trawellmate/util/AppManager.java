package net.promasoft.trawellmate.util;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;


import androidx.core.app.ActivityCompat;


import net.promasoft.trawellmate.LoginAct;
import net.promasoft.trawellmate.db.SharedPrefHelper;
import net.promasoft.trawellmate.db.UserPrefHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AppManager {
    public final static int Default = 0;

    public static final boolean IS_TEST_DOMAIN = false;

//    public static void loadActivityDefaults(Activity activity) {
//        setAppLanguage(activity);
//    }
//
//    public static void changeToLanguage(Activity activity, String lang) {
//
//        SharedPrefHelper.getInstance(activity).setUserLanguage(lang);
//        activity.finish();
//        activity.startActivity(new Intent(activity, activity.getClass()));
//        activity.overridePendingTransition(android.R.anim.fade_in,
//                android.R.anim.fade_out);
//    }
//
//    public static void setAppLanguage(Activity activity) {
//        String lan = SharedPrefHelper.getInstance(activity).getUserLanguage();
//        if (lan.isEmpty()) {
//            return;
//        }
//        Configuration config = activity.getBaseContext().getResources().getConfiguration();
//        Locale locale = new Locale(lan);
//        Locale.setDefault(locale);
//        config.locale = locale;
//        activity.getBaseContext().getResources().updateConfiguration(config, activity.getBaseContext().getResources().getDisplayMetrics());
////        recreate();
//    }
//

//
//    public static boolean verifyLocationServices(Context mContext) {
//        try {
//            final LocationManager manager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
//            return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        } catch (Exception e) {
//        }
//        return false;
//    }
//
//    public static String getFormatedDate() {
//        String date = new SimpleDateFormat(AppConstants.SERVER_DATE_FORMATE).format(new Date());
//        return date;
//    }
//
//    public static long getDateDiffFrmNow(String todate) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
//        try {
//            Date dateStart = simpleDateFormat.parse(todate);
//
//            Calendar mcurrentCalender = Calendar.getInstance();
//            final int mYear = mcurrentCalender.get(Calendar.YEAR);
//            final int mMonth = mcurrentCalender.get(Calendar.MONTH) + 1;
//            final int mDay = mcurrentCalender.get(Calendar.DAY_OF_MONTH);
//            String now = "" + mYear + "-" + mMonth + "-" + mDay;
//
//            Date dateNow = simpleDateFormat.parse(now);
//
//            long different = dateNow.getTime() - dateStart.getTime();
//
//            System.out.println("different : " + different);
//
//            long secondsInMilli = 1000;
//            long minutesInMilli = secondsInMilli * 60;
//            long hoursInMilli = minutesInMilli * 60;
//            long daysInMilli = hoursInMilli * 24;
//
//            long elapsedDays = different / daysInMilli;
//
//            return elapsedDays;
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public static long getDateDiff(String startDate, String endDate) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
//        try {
//            Date dateStart = simpleDateFormat.parse(startDate);
//            Date dateEnd = simpleDateFormat.parse(endDate);
//
//            long different = dateEnd.getTime() - dateStart.getTime();
//
//            System.out.println("different : " + different);
//
//            long secondsInMilli = 1000;
//            long minutesInMilli = secondsInMilli * 60;
//            long hoursInMilli = minutesInMilli * 60;
//            long daysInMilli = hoursInMilli * 24;
//
//            long elapsedDays = different / daysInMilli;
//
//            return elapsedDays;
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public static void clearSession(Context activity) {
//        SharedPrefHelper.getInstance(activity).setIsLogin(false);
//        Intent intent = new Intent(activity, LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                Intent.FLAG_ACTIVITY_CLEAR_TASK |
//                Intent.FLAG_ACTIVITY_NEW_TASK);
//        activity.startActivity(intent);
//    }


    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "" + getDeviceUniqueID(context);
        }
        return tm.getDeviceId();
    }

    public static String getDeviceUniqueID(Context activity) {
        String device_unique_id = "";
        try {
            device_unique_id = Settings.Secure.getString(activity.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device_unique_id;
    }

    public static boolean isNetworkConnected(Context context) {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            connected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception ex) {
        }
        return connected;
    }


    public static int getVersionCode(Context context) {
        String versionName = "";
        int versionCode = -1;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getVersionName(Context context) {
        String versionName = "";
        int versionCode = -1;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static boolean verifyLocationServices(Context mContext) {
        try {
            final LocationManager manager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
            return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
        }
        return false;
    }

    public static void clearSession(Context activity) {
        SharedPrefHelper.getInstance(activity).setIsLogin(false);
        SharedPrefHelper.getInstance(activity).setIsFirstTime(true);
        UserPrefHelper.getInstance(activity).setImageUploaded(false);
        Intent intent = new Intent(activity, LoginAct.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

//    public static void clearSessionToActivation(Context activity) {
//
//        SharedPrefHelper.getInstance(activity).setIsLogin(false);
//        Intent intent = new Intent(activity, ActivationAct.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                Intent.FLAG_ACTIVITY_CLEAR_TASK |
//                Intent.FLAG_ACTIVITY_NEW_TASK);
//        activity.startActivity(intent);
//    }

    public static void OpenPlaystore(Context context) {
        final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
        try {
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Date getDateFrom(String dateTExt) {
        String dtStart = dateTExt;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(dtStart);
            return date;
//            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTimeFrom(String dateTExt) {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("hh:mm:ss a");
        String time = localDateFormat.format(getDateFrom(dateTExt));
        return time;
    }


}
