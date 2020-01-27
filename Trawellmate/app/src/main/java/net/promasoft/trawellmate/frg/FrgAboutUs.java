package net.promasoft.trawellmate.frg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import net.promasoft.trawellmate.R;

public class FrgAboutUs extends Fragment {

    private View view;

    public FrgAboutUs() {
        // Required empty public constructor
    }

    public static FrgAboutUs newInstance() {
        return new FrgAboutUs();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_booking, container, false);


        return view;
    }



}
