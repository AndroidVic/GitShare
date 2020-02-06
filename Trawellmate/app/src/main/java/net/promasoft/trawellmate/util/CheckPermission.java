package net.promasoft.trawellmate.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class CheckPermission {

    public static final int MY_PERMISSIONS_REQUEST = 001;

    public static boolean selfPermissionCheck(Activity activity) {

        List<String> listPermissionsNeeded = new ArrayList<>();
        String[] permissions = new String[]{
//                Manifest.permission.INTERNET,
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.RECORD_AUDIO,
//                Manifest.permission.READ_CONTACTS,
//                Manifest.permission.CALL_PHONE,
//                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.READ_SMS,
//                Manifest.permission.RECEIVE_SMS,
//                Manifest.permission.SEND_SMS,
//                Manifest.permission.CAMERA,
//                Manifest.permission.WAKE_LOCK,
//                Manifest.permission.VIBRATE,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_PHONE_STATE

        };

        for (String permsn : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permsn) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permsn);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {

            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), MY_PERMISSIONS_REQUEST);

        } else {
            // continueService();
            //   finish();
            return true;
        }
        return false;
    }

    public static boolean selfPermissionList(Activity activity, String[] permissions) {

        List<String> listPermissionsNeeded = new ArrayList<>();

        for (String permsn : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permsn) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permsn);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {

            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), MY_PERMISSIONS_REQUEST);

        } else {
            // continueService();
            //   finish();
            return true;
        }
        return false;
    }


}
