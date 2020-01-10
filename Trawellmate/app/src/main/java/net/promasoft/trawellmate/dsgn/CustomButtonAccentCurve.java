package net.promasoft.trawellmate.dsgn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.Button;

import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.util.DiamensionManager;


@SuppressLint("AppCompatCustomView")
public class CustomButtonAccentCurve extends Button {

    private GradientDrawable gdDefault;
    private GradientDrawable gdClicked;
    private boolean BUTTON_DISABLED;

    public CustomButtonAccentCurve(Context context) {
        super(context);
        initThemeColor();
    }

    public CustomButtonAccentCurve(Context context, AttributeSet attrs) {
        super(context, attrs);
        initThemeColor();
    }

    public CustomButtonAccentCurve(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initThemeColor();
    }

    private static int getThemePrimaryColorDark(Context context) {
        int colorAttr;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            colorAttr = R.attr.colorPrimaryDark;
        } else {
            //Get colorAccent defined for AppCompat
//            colorAttr = context.getResources().getIdentifier("colorPrimaryDark", "attr", context.getPackageName());
            colorAttr = context.getResources().getIdentifier("colorAccent", "attr", context.getPackageName());

        }
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(colorAttr, outValue, true);
        return outValue.data;
    }

    private static int getThemePrimaryColor(Context context) {
        int colorAttr;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            colorAttr = android.R.attr.colorPrimary;
        } else {
            //Get colorAccent defined for AppCompat
            colorAttr = context.getResources().getIdentifier("colorPrimary", "attr", context.getPackageName());
        }
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(colorAttr, outValue, true);
        return outValue.data;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (BUTTON_DISABLED) {
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackground(gdClicked);
                break;
            case MotionEvent.ACTION_UP:
                setBackground(gdDefault);
                break;
            case MotionEvent.ACTION_OUTSIDE:
                setBackground(gdDefault);
                break;
            case MotionEvent.ACTION_CANCEL:
                setBackground(gdDefault);
                break;
        }

        return super.onTouchEvent(event);
    }

    void initThemeColor() {
        gdDefault = new GradientDrawable();
        gdDefault.setColor(getThemePrimaryColor(getContext()));
        gdDefault.setCornerRadius(DiamensionManager.getInstance().convertDpToPixel(getContext(),25));
        gdClicked = new GradientDrawable();
        gdClicked.setColor(getThemePrimaryColorDark(getContext()));
        gdClicked.setCornerRadius(DiamensionManager.getInstance().convertDpToPixel(getContext(),25));
        setBackground(gdDefault);
    }


    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            BUTTON_DISABLED = false;
            setAlpha(1f);
        } else {
            BUTTON_DISABLED = true;
            setAlpha(0.4f);
        }
    }
}
