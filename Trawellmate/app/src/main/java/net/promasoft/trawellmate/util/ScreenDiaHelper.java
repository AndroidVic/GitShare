package net.promasoft.trawellmate.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

//
// Created by ViC on 20-Jan-20.
//
public class ScreenDiaHelper {


//    public static int getNavigationBarHEight(Context context) {
//        Resources resources = context.getResources();
//        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            return resources.getDimensionPixelSize(resourceId);
//        }
//        return 0;
//    }
//
//    public static int getNavBarHeight(Context c) {
//        int result = 0;
//        boolean hasMenuKey = ViewConfiguration.get(c).hasPermanentMenuKey();
//        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
//
//        if (!hasMenuKey && !hasBackKey) {
//            //The device has a navigation bar
//            Resources resources = c.getResources();
//
//            int orientation = resources.getConfiguration().orientation;
//            int resourceId;
//            if (isTablet(c)) {
//                resourceId = resources.getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
//            } else {
//                resourceId = resources.getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_width", "dimen", "android");
//            }
//
//            if (resourceId > 0) {
//                return resources.getDimensionPixelSize(resourceId);
//            }
//        }
//        return result;
//    }
//
//    private int getNavigationBarHeight(Context context, int orientation) {
//        Resources resources = context.getResources();
//
//        int id = resources.getIdentifier(
//                orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape",
//                "dimen", "android");
//        if (id > 0) {
//            return resources.getDimensionPixelSize(id);
//        }
//        return 0;
//    }

    private static boolean isTablet(Context c) {
        return (c.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getNavigationBarHeight(Context context) {
        boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0 && !hasMenuKey) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

}
