package net.promasoft.trawellmate.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import net.promasoft.trawellmate.R;


public class LoadingScreen extends RelativeLayout {

    private final RelativeLayout loadingContainer;
    private Context mContext;
    private ViewGroup mRoot;
    private View mChild;

    public LoadingScreen(Activity activity) {
        super(activity);
        mContext = activity;
        mRoot = (ViewGroup) getContentView(activity);
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mChild = mInflater.inflate(R.layout.cus_loading_screen, this, true);

        loadingContainer = (RelativeLayout) mChild.findViewById(R.id.Loading_Container);

        LayoutParams lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        ImageView imageView = (ImageView) findViewById(R.id.ID_GIF_Loading);

        Glide.with(mContext).load(R.raw.loading1).into(imageView);

//        mGifImg = (GifImageView) findViewById(R.id.ID_GIF_Loading);
//        mGifImg.setGifImageResource(R.raw.loading1);
//        mGifImg.setLayerType(View.LAYER_TYPE_SOFTWARE, null);


    }


    public void showLoading() {
        try {
            cancelLoading();
            mRoot.addView(mChild);
//            startBounce();
        } catch (Exception e) {
            // Toast.makeText(mContext, "Already has", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelLoading() {

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


}