package net.promasoft.trawellmate.util;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;

public class AppSnackAlerts {


    public static void showMessage(View view, String msg) {
        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_SHORT);
        try {
            SnackbarContentLayout contentLayout = (SnackbarContentLayout) ((FrameLayout) snackbar.getView()).getChildAt(0);
            @SuppressLint("RestrictedApi") TextView tv = contentLayout.getMessageView();
            tv.setTextColor(Color.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        snackbar.setText(msg);
        snackbar.show();
    }
}
