package net.promasoft.trawellmate.util;

import android.os.Handler;
import android.widget.Toast;

//
// Created by ViC on 16-Jan-20.
//
public class DelayHelper {

    private static Handler handler;
    private static Runnable runnable;

    public static void delayStartWith(int interval, DelayListner delayListner) {
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                if (delayListner != null) {
                    delayListner.onStartTask();
                }
            }
        };
        handler.postDelayed(runnable, interval);

    }

    public static void closeAllDelays() {
        if (handler != null) {
            if (runnable != null) {
                handler.removeCallbacks(runnable);
            }
        }
    }

    public interface DelayListner {
        void onStartTask();
    }

}

