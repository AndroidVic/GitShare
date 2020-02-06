package net.promasoft.trawellmate.frg;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.util.AlineActivityHelper;
import net.promasoft.trawellmate.util.DrawerFrgListner;

public class FrgPromptLogin extends Fragment {

    private String desc;
    private String title;
    private LoginPromptListner loginPromptListner;
    private Activity mAction;
    private View view;

    public FrgPromptLogin(Activity activity, String title, String desc, LoginPromptListner loginPromptListner) {
        // Required empty public constructor
        this.mAction = activity;
        this.title = title;
        this.desc = desc;
        this.loginPromptListner = loginPromptListner;
        AlineActivityHelper.setStatusBar(getActivity());


    }

    public static FrgPromptLogin newInstance(Activity activity, String title, String desc, LoginPromptListner loginPromptListner) {
        return new FrgPromptLogin(activity, title, desc, loginPromptListner);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.prompt_login, container, false);

        TextView title = view.findViewById(R.id.ID_prompt_title);
        title.setText(this.title);

        TextView desc = view.findViewById(R.id.ID_prompt_description);
        desc.setText(this.desc);
        Button login = view.findViewById(R.id.ID_prompt_login);
        login.setOnClickListener(view1 -> {
            if (loginPromptListner != null) {
                loginPromptListner.onLoginClicked();
            }
        });

        return view;
    }

    public interface LoginPromptListner {

        void onLoginClicked();

    }


}
