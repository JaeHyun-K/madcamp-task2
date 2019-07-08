package com.example.myapplication;

import android.annotation.SuppressLint;
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

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ItemViewHolder> {
    private Context context;
    private ArrayList<conlist2> listData2 = new ArrayList<>();
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_todolist, parent, false);
        this.context= parent.getContext();
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(listData2.get(position));
    }
    @Override
    public int getItemCount() {
        return listData2.size();
    }
    void addItem(conlist2 data2) {
        listData2.add(data2);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView text_todo;
        private TextView text_date;
        private conlist2 data2;
        public LinearLayout linearLayout2;
        @SuppressLint("WrongViewCast")
        ItemViewHolder(View itemView) {
            super(itemView);
            text_todo = itemView.findViewById(R.id.text_todo);
            text_date = itemView.findViewById(R.id.text_date);
            linearLayout2=(LinearLayout)itemView.findViewById(R.id.linearItem2);
        }
        void onBind(conlist2 data2) {
            this.data2=data2;
            text_todo.setText(data2.getText_todo());
            text_date.setText(data2.getText_date());

        }

//        @Override
//        public void onClick(View v){
//            switch (v.getId()){
//                case R.id.linearItem:
//                    Toast.makeText(context,"NAME: "+data.getName()+"\nNumber"+data.getNumber(),Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.text1:
//                    Toast.makeText(context,data.getName(),Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.text2:
//                    Toast.makeText(context,data.getNumber(),Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
    }
}