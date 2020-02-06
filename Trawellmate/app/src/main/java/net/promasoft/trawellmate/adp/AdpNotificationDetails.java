package net.promasoft.trawellmate.adp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.argapp.DataNotification;

import java.util.List;

public class AdpNotificationDetails extends RecyclerView.Adapter<AdpNotificationDetails.NotifyViewHolder> {
    private List<DataNotification> items;

    // private ArrayList<String> names;
    public AdpNotificationDetails(List<DataNotification> list) {
        this.items = list;
    }

    @NonNull
    @Override
    public NotifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_notification_card, parent, false);
        NotifyViewHolder myViewHolder = new NotifyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyViewHolder holder, int position) {
        DataNotification result = items.get(position);
        holder.destination.setText(result.getDestinatn());
        holder.price.setText(result.getPackPrice());
        holder.pTitle.setText(result.getPackTitle());
        holder.pDates.setText(result.getPackdates());
        holder.nTime.setText(result.getNotfctnTime());
        if (position == items.size()-1) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 100);
            holder.itemView.setLayoutParams(layoutParams);
        }else {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            holder.itemView.setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class NotifyViewHolder extends RecyclerView.ViewHolder {
        public TextView destination;
        public TextView price;
        public TextView pTitle;
        public TextView pDates;
        public TextView nTime;

        public NotifyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.destination = (TextView) itemView.findViewById(R.id.ID_loc_dest);
            this.price = (TextView) itemView.findViewById(R.id.ID_amt_package);
            this.pTitle = (TextView) itemView.findViewById(R.id.ID_pack_name);
            this.pDates = (TextView) itemView.findViewById(R.id.ID_package_dates);
            this.nTime = (TextView) itemView.findViewById(R.id.ID_notifctn_time);
        }
    }
}
