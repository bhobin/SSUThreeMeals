package com.example.user.androidproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.androidproject.R;
import com.example.user.androidproject.ShowMenuActivity;
import com.example.user.androidproject.StoreData;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-20.
 */
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ListItemViewHolder> {
    private Context mContext = null;
    private ArrayList<StoreData> mListData;

    public StoreAdapter(Context mContext, ArrayList<StoreData> listData, RecyclerView recyclerView) {
        super();
        this.mContext = mContext;
        this.mListData = listData;

    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ListItemViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, final int position) {

        final StoreData mData = mListData.get(position);

        holder.title.setText(mData.getTitle());
        holder.time.setText(mData.getTime());
        holder.like.setText(String.valueOf(mData.getLike_count()));
        Glide.with(mContext).load(mData.getImage()).into(holder.image);
        holder.mContext = mContext;

        holder.layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowMenuActivity.class);

                intent.putExtra("id", mData.getId());
                intent.putExtra("category", mData.getCategory());
                intent.putExtra("title", mData.getTitle());
                intent.putExtra("time", mData.getTime());
                intent.putExtra("information", mData.getInformation());
                intent.putExtra("number", mData.getNumber());
                intent.putExtra("like_count", mData.getLike_count());
                intent.putExtra("location", mData.getLocation());
                intent.putExtra("image", mData.getLocation());
                intent.putExtra("report", mData.getReport());
                intent.putExtra("image", mData.getImage());

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }


    public final static class ListItemViewHolder extends RecyclerView.ViewHolder {
        // TextView tvDate;
        LinearLayout layout;
        TextView title;
        TextView time;
        TextView like;
        ImageView image;
        Context mContext = null;

        public ListItemViewHolder(View itemView, int viewType) {
            super(itemView);

            layout = (LinearLayout) itemView.findViewById(R.id.layout_food_item);
            title = (TextView) itemView.findViewById(R.id.title);
            time = (TextView) itemView.findViewById(R.id.time);
            image = (ImageView) itemView.findViewById(R.id.image);
            like = (TextView) itemView.findViewById(R.id.like_text);
        }
    }
}