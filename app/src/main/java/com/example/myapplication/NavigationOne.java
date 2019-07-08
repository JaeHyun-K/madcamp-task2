package com.example.myapplication;

import android.content.Intent;
import android.text.TextUtils;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.myapplication.Retrofit.IMyService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import android.view.ViewGroup;
import java.text.ParseException;
import java.util.HashMap;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.text.TextUtils;
import android.widget.Button;
import android.view.View;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.OrientationHelper;
import android.hardware.SensorManager;

import io.reactivex.disposables.CompositeDisposable;


public class NavigationOne extends Fragment {
    private RecyclerView mRecyclerview;
    private RecyclerAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Contactlist> contactlists=new ArrayList<>();
    private FloatingActionButton floatingActionButton;
    private String jsonStr;
    String account;
    private Contactlist data;
    private Toolbar toolbar;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    IMyService iMyService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(savedInstanceState);
        account=getActivity().getIntent().getStringExtra("account");
        View navigation_contact = inflater.inflate(R.layout.navigation_contact, container, false);
        RecyclerView recyclerView=(RecyclerView)navigation_contact.findViewById(R.id.recyclerview_main_list);


        floatingActionButton=(FloatingActionButton) navigation_contact.findViewById(R.id.Addbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("account",account);
                startActivityForResult(intent,1234);
            }
        });

        mLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter=new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        //parseData();
        return navigation_contact;
    }

    private void parseData(String string){

        List<String> listName=new ArrayList<>();
        List<String> listNumber=new ArrayList<>();
        List<String> listEmail=new ArrayList<>();
        //List<Integer> listResId=new ArrayList<>();
        int chnNum;
//파싱하기
        if (jsonStr!=null && !TextUtils.isEmpty(jsonStr)) {
            try {
                JSONObject object = new JSONObject(jsonStr);
                JSONArray jsonArray  = object.getJSONArray("contact");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String email=jsonObject.getString("email");
                    String number = jsonObject.getString("number");
                    //String resId = jsonObject.getString("img");
                    //chnNum=Integer.parseInt(resId);
                    listName.add(name);
                    listNumber.add(number);
                    listEmail.add(email);
                    //listResId.add(chnNum);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //파싱 끝낸후

        for(int i=0; i<listName.size();i++){

            Contactlist conlist=new Contactlist();
            conlist.setResId(R.drawable.ic_person_outline_black_24dp);
            conlist.setEmail(listEmail.get(i));
            conlist.setName(listName.get(i));
            conlist.setNumber(listNumber.get(i));

            contactlists.add(conlist);
        }

        adapter.notifyDataSetChanged();
//데이터 받아오기 끝.
    }
}