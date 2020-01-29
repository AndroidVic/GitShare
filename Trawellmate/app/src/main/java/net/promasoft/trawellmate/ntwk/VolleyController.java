package net.promasoft.trawellmate.ntwk;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

//
// Created by ViC on 29-Jan-20.
//
public class VolleyController {
    private static RequestQueue ourInstance;

    public static RequestQueue getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance =  Volley.newRequestQueue(context);
        }
        return ourInstance;
    }


}
