package com.example.myapplication;

import android.text.TextUtils;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import android.view.ViewGroup;
import java.text.ParseException;
import java.util.HashMap;
import android.widget.EditText;
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
public class NavigationOne extends Fragment {
    private RecyclerView mRecyclerview;
    private RecyclerAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    //private ArrayList<conlist>
    private String jsonStr;
    private conlist data;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(savedInstanceState);
        View navigation_contact = inflater.inflate(R.layout.navigation_contact, container, false);
        RecyclerView recyclerView=(RecyclerView)navigation_contact.findViewById(R.id.recyclerview_main_list);


        //Button buttonInsert=(Button)navigation_contact.findViewById(R.id.buttonAdd);
        mLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter=new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        getData();
        setData();
        return navigation_contact;
    }
    private void getData(){
        JSONObject obj=new JSONObject();

        JSONArray array=new JSONArray();
        try{
            obj.put("name","리본겨울");
            obj.put("number","01012345678");
            obj.put("img",R.drawable.wimage_1);

            array.put(obj);

        }catch (JSONException e){
            e.printStackTrace();
        }
        JSONObject contectobj=new JSONObject();
        try{
            contectobj.put("contact",array);
        }catch (JSONException e){
            e.printStackTrace(); }

        jsonStr=contectobj.toString();


    }
    private void setData(){
        //String 형식으로 받은 Data를 다시 JSON으로 변환 후, Recyclerview에서 출력
        //여기 Array 만드는 부분은 다를수도 있음.(Array 종류가)
        List<String> listName=new ArrayList<>();
        List<String> listNumber=new ArrayList<>();
        List<Integer> listResId=new ArrayList<>();
        int chnNum;
//파싱하기
        if (jsonStr!=null && !TextUtils.isEmpty(jsonStr)) {
            try {
                JSONObject object = new JSONObject(jsonStr);
                JSONArray jsonArray  = object.getJSONArray("contact");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String number = jsonObject.getString("number");
                    String resId = jsonObject.getString("img");
                    chnNum=Integer.parseInt(resId);
                    listName.add(name);
                    listNumber.add(number);
                    listResId.add(chnNum);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //파싱 끝낸후

        for(int i=0; i<listName.size();i++){
            data=new conlist(listName.get(i),listNumber.get(i),listResId.get(i));

            adapter.addItem(data);
        }

        adapter.notifyDataSetChanged();
//데이터 받아오기 끝.
    }
}