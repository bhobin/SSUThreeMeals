package com.example.user.androidproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.androidproject.R;

/**
 * Created by user on 2016-11-20.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ListItemViewHolder> {
    private Context mContext = null;
    private Bitmap[] mListData;

    public PictureAdapter(Context mContext, Bitmap[] bitmaps, RecyclerView recyclerView) {
        super();
        this.mContext = mContext;
        this.mListData = bitmaps;

    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        return new ListItemViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, final int position) {
        holder.image.setImageBitmap(mListData[position]);
        holder.mContext = mContext;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mListData.length;
    }


    public final static class ListItemViewHolder extends RecyclerView.ViewHolder {
        // TextView tvDate;
        ImageView image;
        TextView text;
        Context mContext = null;

        public ListItemViewHolder(View itemView, int viewType) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.text);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}