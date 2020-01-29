package net.promasoft.trawellmate.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//
// Created by ViC on 24-Sep-19.
//
public class ToastHelper {

    private Context context;
    private Toast toast;
    public static final String CLR_DEFAULT = "#414141";
    public static final String CLR_GREEN = "#338542";
    public static final String CLR_RED = "#F44336";
    public static final String CLR_BLUE = "#2196F3";
    private int duration;
    private final int DEFAULT_DURATION = 1800;
    private final float DEFAULT_CR_RADIUS = 10.0f;
    private int color;
    private float radius;


    public ToastHelper(Context context, String message) {
        this.context = context;
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        duration = DEFAULT_DURATION;
        color = Color.parseColor(CLR_DEFAULT);
        radius = DEFAULT_CR_RADIUS;
    }

    /**
     * Duration of the toast shown.
     *
     * @param duration (in mili seconds) - should be between 100 to 3500
     */
    public ToastHelper setDuration(@IntRange(from = 100, to = 3500) int duration) {
        if (duration < 100) {
            this.duration = 100;
        } else if (duration > 3500) {
            this.duration = 3500;
        } else {
            this.duration = duration;
        }
        return this;
    }

    /**
     * Set Background Color of the Toast.
     *
     * @param color The color string to parse
     */
    public ToastHelper setBgColor(String color) {
        this.color = Color.parseColor(color);
        return this;
    }

    /**
     * Set Background Color of the Toast.
     *
     * @param resID The identifier of the resource.
     */
    public ToastHelper setBgColor(@ColorRes int resID) {
        this.color = context.getResources().getColor(resID);
        return this;
    }

    /**
     * Corner radius of the background.
     *
     * @param radius The radius to apply - should be between 0 to 100
     */
    public ToastHelper setBgCornerRadius(@FloatRange(from = 0, to = 100) float radius) {
        if (radius < 0) {
            this.radius = 0;
        } else if (radius > 100) {
            this.radius = 100;
        } else {
            this.radius = radius;
        }
        return this;
    }

    /**
     * Set Text Color of the Toast.
     *
     * @param resID The identifier of the resource.
     */
    public ToastHelper setTextColor(@ColorRes int resID) {
        try {
            TextView tv = toast.getView().findViewById(android.R.id.message);
            tv.setTextColor(context.getResources().getColor(resID));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Set Text Color of the Toast.
     *
     * @param color The color string to parse.
     */
    public ToastHelper setTextColor(String color) {
        try {
            TextView tv = toast.getView().findViewById(android.R.id.message);
            tv.setTextColor(Color.parseColor(color));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @StringDef({CLR_DEFAULT, CLR_GREEN, CLR_RED, CLR_BLUE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BgStatColors {
    }

    public ToastHelper setDefaultColr(@BgStatColors String color) {
        this.color = Color.parseColor(color);
        return this;
    }

    private Drawable getBgFromParams(int color) {
        RoundRectShape rectShape = new RoundRectShape(new float[]{radius, radius, radius, radius, radius, radius, radius, radius}, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(rectShape);
        shapeDrawable.getPaint().setColor(color);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable.setPadding(0, 0, 0, 5);
        shapeDrawable.getPaint().setAntiAlias(true);
        return shapeDrawable;

    }

    public void show() {
        toast.getView().setBackground(getBgFromParams(color));
        toast.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (toast != null) {
                    toast.cancel();
                }
            }
        }, duration);
    }

}
