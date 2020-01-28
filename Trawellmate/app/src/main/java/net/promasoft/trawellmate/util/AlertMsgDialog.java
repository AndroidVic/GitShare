package net.promasoft.trawellmate.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.promasoft.trawellmate.R;

public class AlertMsgDialog extends RelativeLayout implements View.OnClickListener {
    private ActionListner actionListner;
    private Activity mContext;
    private ViewGroup mRoot;
    private View mChild;
    ImageView img, ivClose;
    TextView tv_title, tv_content;
    Button btn_positive;
    public static final int DIA_LOGOUT = 1;
    public static final int DIA_INFO = 2;

    public AlertMsgDialog(Activity activity, int dialogType, String alertTile, String alertContent, String positiveBtText, ActionListner actionListner) {
        super(activity);
        mContext = activity;
        this.actionListner = actionListner;
        mRoot = (ViewGroup) getContentView(activity);
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mChild = mInflater.inflate(R.layout.activity_alert_msg, this, true);
        init();
        btn_positive.setText(positiveBtText);
        switch (dialogType) {
            case DIA_LOGOUT:
                img.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_logout));
                break;
            case DIA_INFO:
                img.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_info));
                break;
        }
        tv_title.setText(alertTile);
        tv_content.setText(alertContent);

    }

    private void init() {
        img = findViewById(R.id.iv_alertImg);
        tv_title = findViewById(R.id.tv_alertTitle);
        tv_content = findViewById(R.id.alert_content);
        btn_positive = findViewById(R.id.ID_postive_btn);
        ivClose = findViewById(R.id.iv_close);

        btn_positive.setOnClickListener(v -> {
            closePage();
            if (actionListner != null) {
                actionListner.onPositive();
            }

        });
        ivClose.setOnClickListener(v -> {
            closePage();
            if (actionListner != null) {
                actionListner.onNegative();
            }

        });

    }

    public void showPage() {
        try {
            /* closePage();*/
            mRoot.addView(mChild);
//            startBounce();
        } catch (Exception e) {
            // Toast.makeText(mContext, "Already has", Toast.LENGTH_SHORT).show();
        }
    }

    public void closePage() {

        try {
            mRoot.removeView(mChild);
        } catch (Exception e) {

        }
        //  stopAnim();

    }

    private View getContentView(Activity a) {
        int id = a.getResources().getIdentifier("content", "id", "android");
        return a.findViewById(id);
    }

    public interface ActionListner {
        void onPositive();

        void onNegative();
    }

    @Override
    public void onClick(View v) {

    }
}
