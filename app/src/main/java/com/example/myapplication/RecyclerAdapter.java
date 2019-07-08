package com.example.myapplication;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.widget.Toast;
import android.widget.ImageView;
import java.lang.Object;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    private Context context;
    private ImageButton calling;
    private ImageButton remove;
    private ImageButton edit;

    private AdapterView.OnItemClickListener rmvItemListener;
    private AdapterView.OnItemClickListener editItemListener;

    private ArrayList<Contactlist> recyclerItems = new ArrayList<>();
    private ImageButton btnTest;
    private ImageButton removeBtn;
    private ImageButton callBtn;
    private final Context mContext;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener removeItemClickListener;



    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }


    public RecyclerAdapter(Context context, OnItemClickListener onItemClickListener, OnItemClickListener removeItemClickListener) {
        mContext = context;
        this.onItemClickListener = onItemClickListener;
        this.removeItemClickListener = removeItemClickListener;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final int Position = position;
        holder.onBind(recyclerItems.get(position));
        RecyclerAdapter.ItemViewHolder vholder = (RecyclerAdapter.ItemViewHolder) holder;

        vholder.getBtnTest().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, Position);
            }
        });

        vholder.getRemoveBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItemClickListener.onItemClick(v, Position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }

    void addItem(Contactlist item) {
        recyclerItems.add(item);
    }

    void resetItem() {
        recyclerItems.clear();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Contactlist recyclerItem;
        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;
        private LinearLayout linearLayout;


        ItemViewHolder(View itemView) {
            super(itemView);

            btnTest = itemView.findViewById(R.id.call_button);
            removeBtn = itemView.findViewById(R.id.delete_contact);
            textView1 = itemView.findViewById(R.id.text1);
            textView2 = itemView.findViewById(R.id.text2);
            imageView = itemView.findViewById(R.id.imageView);
            linearLayout = itemView.findViewById(R.id.Linearlayout_id);

        }

        public ImageButton getBtnTest() {
            return btnTest;
        }

        public ImageButton getRemoveBtn() {
            return removeBtn;
        }

        void onBind(Contactlist recyclerItem) {
            this.recyclerItem = recyclerItem;
            textView1.setText(recyclerItem.getName());
            textView2.setText(recyclerItem.getNumber());
            //imageView.setImageResource(recyclerItem.getPhoto());

            textView1.setOnClickListener(this);
            textView2.setOnClickListener(this);
            //imageView.setOnClickListener(this);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.Linearlayout_id:
                    show(view);
                    break;
                case R.id.text1:
                    show(view);
                    break;
                case R.id.text2:
                    show(view);
                    break;
//                case R.id.imageView:
//                    show(view);
//                   break;
            }
        }

        void show(View view) {
            final Context context = view.getContext();

            final List<String> InfoItems = new ArrayList<>();
            List<String> ToastItems = new ArrayList<>();
            final List<String> nameofItems = new ArrayList<>();

            InfoItems.add(recyclerItem.getName());
            InfoItems.add(recyclerItem.getNumber());
            InfoItems.add(recyclerItem.getEmail());


            ToastItems.add("Name: \n" + recyclerItem.getName());
            ToastItems.add("Phone Number: \n" + recyclerItem.getNumber());
            ToastItems.add("Email: \n" + recyclerItem.getEmail());


            nameofItems.add("Name");
            nameofItems.add("Phone Number");
            nameofItems.add("Email");


            final CharSequence[] Infos = ToastItems.toArray(new String[ToastItems.size()]);
            final CharSequence[] ContactNumber = InfoItems.toArray(new String[InfoItems.size()]);
            final CharSequence[] NameItem = nameofItems.toArray(new String[InfoItems.size()]);

            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialog);
            builder.setTitle("Contact Info of " + recyclerItem.getName());
            builder.setItems(Infos, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 1) {
                        Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ContactNumber[4].toString()));
                        context.startActivity(dial);

                    } else {
                        String selectedInfo = NameItem[i].toString();
                        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("label", ContactNumber[i].toString());
                        clipboardManager.setPrimaryClip(clipData);
                        Toast.makeText(context, selectedInfo + " has been copied on Clipboard", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(context, "Closed " + recyclerItem.getName() + "'s Contact Info", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }
    }
}