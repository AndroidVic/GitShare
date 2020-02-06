package net.promasoft.trawellmate.adp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.argapp.DataSearch;

import java.util.ArrayList;
import java.util.List;

//
// Created by ViC on 05-Feb-20.
//
public class AdpSearchDeatils extends RecyclerView.Adapter<AdpSearchDeatils.MyViewHolder> {
    private List<DataSearch> items;
    private ArrayList<String> names;

    public AdpSearchDeatils(List<DataSearch> items) {
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataSearch item = items.get(position);
        holder.title.setText(item.getName());
        holder.desc.setText(item.getDescription());

    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView desc;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.textViewItem);
            this.desc = (TextView) itemView.findViewById(R.id.textViewDescrip);
        }
    }
    public void filterList(ArrayList<String> filterdNames) {
        this.names = filterdNames;
        notifyDataSetChanged();
    }


}