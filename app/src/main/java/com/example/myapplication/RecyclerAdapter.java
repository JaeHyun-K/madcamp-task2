package com.example.myapplication;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.widget.Toast;
import android.widget.ImageView;
import java.lang.Object;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    private Context context;
    private ArrayList<conlist> listData = new ArrayList<>();
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        this.context= parent.getContext();
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }
    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }
    void addItem(conlist data) {
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textView1;
        private TextView textView2;
        private conlist data;
        public LinearLayout linearLayout;
        private ImageView imageView;
        ItemViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text1);
            textView2 = itemView.findViewById(R.id.text2);
            imageView=itemView.findViewById(R.id.image);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearItem);
        }
        void onBind(conlist data) {
            this.data=data;
            textView1.setText(data.getName());
            textView2.setText(data.getNumber());
            imageView.setImageResource(data.getResId());

            itemView.setOnClickListener(this);
            textView1.setOnClickListener(this);
            textView2.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.linearItem:
                    Toast.makeText(context,"NAME: "+data.getName()+"\nNumber"+data.getNumber(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.text1:
                    Toast.makeText(context,data.getName(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.text2:
                    Toast.makeText(context,data.getNumber(),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}