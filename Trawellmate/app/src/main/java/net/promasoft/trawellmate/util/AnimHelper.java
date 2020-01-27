package net.promasoft.trawellmate.util;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.View;
import android.view.animation.TranslateAnimation;

//
// Created by ViC on 22-Jan-20.
//
public class AnimHelper {

    public static void slideUp(View view) {
        view.animate().translationY(0).start();


    }

    public static void slideDown( View view) {
        int hei = view.getHeight() <= 0 ? 100 : view.getHeight();
        view.animate().translationY(hei).start();
//        view.animate().translationY(100).start();

    }
}
