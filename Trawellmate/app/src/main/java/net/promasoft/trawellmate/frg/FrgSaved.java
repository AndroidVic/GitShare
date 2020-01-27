package net.promasoft.trawellmate.frg;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import net.promasoft.trawellmate.R;

public class FrgSaved extends Fragment {

    private Activity mAction;
    private View view;

    public FrgSaved(Activity activity) {
        // Required empty public constructor
        this.mAction = activity;
    }

    public static FrgSaved newInstance(Activity activity) {
        return new FrgSaved(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_saved, container, false);
        return view;
    }



}
