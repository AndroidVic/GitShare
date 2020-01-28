package net.promasoft.trawellmate.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import static net.promasoft.trawellmate.util.CheckPermission.MY_PERMISSIONS_REQUEST;

public class CheckPermissionHelper {

    public static boolean isGranded(FragmentActivity activity, String[] permissions) {

        List<String> listPermissionsNeeded = new ArrayList<>();

        for (String permsn : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permsn) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permsn);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }


}
