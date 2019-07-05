package com.example.myapplication;


import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import java.util.ArrayList;

public class NavigationTwo_RecyclerViewAdapter extends RecyclerView.Adapter<NavigationTwo_RecyclerViewAdapter.ImageViewHolder> {

    private Context mContext;
    private ArrayList<NavigationTwo_CreateItem> mGalleryList;


    public NavigationTwo_RecyclerViewAdapter(Context mContext, ArrayList<NavigationTwo_CreateItem> mGalleryList) {
        this.mContext = mContext;
        this.mGalleryList = mGalleryList;
    }

    @Override
    public NavigationTwo_RecyclerViewAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_gallery_grindlistitem,
                parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {

        holder.title.setText(mGalleryList.get(position).getImage_title());
        holder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.img.setImageResource((mGalleryList.get(position).getImage_ID()));

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, NavigationTwo_Detail.class);
                mIntent.putExtra("Image",mGalleryList.get(position).getImage_ID());
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGalleryList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView img;

        public ImageViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.text);
            img = (ImageView) view.findViewById(R.id.image);
        }
    }
}

