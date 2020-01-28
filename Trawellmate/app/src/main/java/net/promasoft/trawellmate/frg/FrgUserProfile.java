package net.promasoft.trawellmate.frg;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.util.DrawerFrgListner;

public class FrgUserProfile extends Fragment {

    private Activity mAction;
    private View view;
    private DrawerFrgListner mDrawerListner;

    public FrgUserProfile(Activity activity) {
        // Required empty public constructor
        this.mAction = activity;
    }

    public static FrgUserProfile newInstance(Activity activity) {
        return new FrgUserProfile(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_user_profile, container, false);
        ImageView menuBar = view.findViewById(R.id.ID_arw_bck);
        menuBar.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_menu));
        menuBar.setOnClickListener(view1 -> {
            mDrawerListner.onDrawerClicked();
        });

        return view;
    }

    public void setDrawerListner(DrawerFrgListner drawerListner) {
        this.mDrawerListner = drawerListner;
    }


}
