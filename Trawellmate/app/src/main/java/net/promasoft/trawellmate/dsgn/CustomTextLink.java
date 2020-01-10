package net.promasoft.trawellmate.dsgn;

import android.content.Context;
import android.os.Build;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import net.promasoft.trawellmate.R;


public class CustomTextLink extends AppCompatTextView {

    private int cDefault;
    private int cClicked;

    public CustomTextLink(Context context) {
        super(context);
        initThemeColor();
    }

    public CustomTextLink(Context context, AttributeSet attrs) {
        super(context, attrs);
        initThemeColor();
    }

    public CustomTextLink(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initThemeColor();
    }

    private int getThemeAccentColorDark(Context context) {
        int colorAttr;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            colorAttr = R.attr.colorAccent;
        } else {
            //Get colorAccent defined for AppCompat
            colorAttr = context.getResources().getIdentifier("colorAccentDark", "attr", context.getPackageName());
        }
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(colorAttr, outValue, true);
        return outValue.data;
    }

    private int getThemePrimaryDarkColor(Context context) {
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

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setTextColor(cClicked);
                break;
            case MotionEvent.ACTION_UP:
                setTextColor(cDefault);
                break;
            case MotionEvent.ACTION_OUTSIDE:
                setTextColor(cDefault);
                break;
            case MotionEvent.ACTION_CANCEL:
                setTextColor(cDefault);
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void setText(CharSequence text, TextView.BufferType type) {

        SpannableString content = new SpannableString(text);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        super.setText(content, type);
    }

    void initThemeColor() {

        super.setClickable(true);

        cDefault = getThemePrimaryDarkColor(getContext());

        cClicked = getThemeAccentColorDark(getContext());

        //   setBackground(gdDefault);
    }
}
