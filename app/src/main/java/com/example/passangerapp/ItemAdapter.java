package com.example.passangerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private static final int VIEW_TYPE_SMALL = 1;
    private int count = 1;

    public static String TYPE = "allBills";

    private List<Item> mItems;
    private GridLayoutManager mLayoutManager;

    public ItemAdapter(List<Item> items, GridLayoutManager layoutManager) {
        mItems = items;
        mLayoutManager = layoutManager;
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_SMALL;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);

        return new ItemViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = mItems.get(position % 4);

        holder.date.setText(item.getDate());
        holder.to.setText(item.getTo());
        holder.from.setText(item.getFrom());
        holder.fromTime.setText(item.getDepartureTime());
        holder.toTime.setText(item.getArrivalTime());
        holder.amount.setText(item.getAmount());

        /*if(TYPE.equalsIgnoreCase("allBills")){
            if((count % 2) == 1){
                holder.layout.setBackgroundResource(R.drawable.card_shape);
                holder.img.setImageResource(R.drawable.right_mark);
                count++;
            }else{
                holder.layout.setBackgroundResource(R.drawable.account_shape);
                count++;
            }
        }
        if(TYPE.equalsIgnoreCase("confirmBill")){
            holder.layout.setBackgroundResource(R.drawable.card_shape);
        }*/
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView from;
        TextView to;
        TextView fromTime;
        TextView toTime;
        TextView amount;

        ItemViewHolder(View itemView, int viewType) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.item_date);
            from = (TextView) itemView.findViewById(R.id.item_from);
            to = (TextView) itemView.findViewById(R.id.item_to);
            fromTime = (TextView) itemView.findViewById(R.id.item_departure_time);
            toTime = (TextView) itemView.findViewById(R.id.item_arrival_time);
            amount = (TextView) itemView.findViewById(R.id.item_amount);
        }
    }
}
