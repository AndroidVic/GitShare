package net.promasoft.trawellmate.adp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.github.vipulasri.timelineview.TimelineView;


import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.argapp.DataDayWise;
import net.promasoft.trawellmate.util.DiamensionManager;
import net.promasoft.trawellmate.util.ViewECAnimator;

import java.util.List;

public class AdpDaWiseDetails extends RecyclerView.Adapter<AdpDaWiseDetails.TimeLineViewHolder> {
    private final Context context;
    private final ClickLstnr lstnr;
    private List<DataDayWise> items;

    public AdpDaWiseDetails(Context context, List<DataDayWise> items, ClickLstnr lstnr) {
        this.items = items;
        this.context = context;
        this.lstnr = lstnr;
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_day_wise, parent, false);
        return new TimeLineViewHolder(view, viewType);
    }


    @Override
    public void onBindViewHolder(final TimeLineViewHolder holder, int position) {

        DataDayWise item = items.get(position);

        holder.mdwTitle.setText(item.mTitle);
        holder.mdwDesc.setText(item.mDescription);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!holder.mIsExpanded) {
                    holder.mIsExpanded = true;
                    expand(holder.mDwContainer);
                    holder.mDwDropImg.setImageDrawable(getDrawable(context, R.drawable.ic_arrow_up));
                } else {
                    holder.mIsExpanded = false;
                    collapse(holder.mDwContainer);
                    holder.mDwDropImg.setImageDrawable(getDrawable(context, R.drawable.ic_arrow_down));
                }

            }
        });

    }

    public void expand(final View v) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = v.getMeasuredHeight();

        ViewECAnimator.expand(v, (int) DiamensionManager.getInstance().convertDpToPixel(context, 50), targetHeight, 1);

    }

    public void collapse(final View v) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = v.getMeasuredHeight();

        ViewECAnimator.collapse(v, (int) DiamensionManager.getInstance().convertDpToPixel(context, 50), targetHeight, 1);


    }

    private Drawable getDrawable(Context context, int drawableResId) {
        return VectorDrawableCompat.create(context.getResources(), drawableResId, context.getTheme());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public interface ClickLstnr {
        void onClickItem(View view, int type);
    }

    public class TimeLineViewHolder extends RecyclerView.ViewHolder {

        private TextView mdwDesc, mdwTitle;
        public TimelineView mTimelineView;
        LinearLayout mDwContainer;
        ImageView mDwDropImg;
        boolean mIsExpanded = false;

        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);

            mTimelineView = (TimelineView) itemView.findViewById(R.id.timeline);
            mDwContainer = itemView.findViewById(R.id.ID_dw_container);

            mdwTitle = itemView.findViewById(R.id.ID_dw_title);
            mdwDesc = itemView.findViewById(R.id.ID_dw_desc);
            mDwDropImg = itemView.findViewById(R.id.ID_dw_arrow);


            mTimelineView.initLine(viewType);


        }
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

}