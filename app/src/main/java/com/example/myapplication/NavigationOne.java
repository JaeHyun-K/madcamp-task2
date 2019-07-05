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

        toolbar = (Toolbar) navigation_contact.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Contact");


        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);;
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
        JSONObject obj2=new JSONObject();
        JSONObject obj3=new JSONObject();
        JSONObject obj4=new JSONObject();
        JSONObject obj5=new JSONObject();
        JSONObject obj6=new JSONObject();
        JSONObject obj7=new JSONObject();
        JSONObject obj8=new JSONObject();
        JSONObject obj9=new JSONObject();
        JSONObject obj10=new JSONObject();
        JSONArray array=new JSONArray();
        try{
            obj.put("name","리본겨울");
            obj.put("number","01012345678");
            obj.put("img",R.drawable.wimage_1);
            obj2.put("name","뽀짝겨울");
            obj2.put("number","01013572468");
            obj2.put("img",R.drawable.wimage_2);
            obj3.put("name","토끼귀겨울");
            obj3.put("number","01098765432");
            obj3.put("img",R.drawable.wimage_3);
            obj4.put("name","사자겨울");
            obj4.put("number","01024681357");
            obj4.put("img",R.drawable.wimage_4);
            obj5.put("name","금겨울");
            obj5.put("number","0102481632");
            obj5.put("img",R.drawable.wimage_5);
            obj6.put("name","쳐다보겨울");
            obj6.put("number","04212345678");
            obj6.put("img",R.drawable.wimage_6);
            obj7.put("name","바다산책하는 겨울");
            obj7.put("number","04213572468");
            obj7.put("img",R.drawable.wimage_7);
            obj8.put("name","꼬리염색겨울");
            obj8.put("number","04224681357");
            obj8.put("img",R.drawable.wimage_8);
            obj9.put("name","카이스트산책겨울");
            obj9.put("number","04298765432");
            obj9.put("img",R.drawable.wimage_9);
            obj10.put("name","찡긋겨울");
            obj10.put("number","04201294837");
            obj10.put("img",R.drawable.wimage_10);
            array.put(obj);
            array.put(obj2);
            array.put(obj3);
            array.put(obj4);
            array.put(obj5);
            array.put(obj6);
            array.put(obj7);
            array.put(obj8);
            array.put(obj9);
            array.put(obj10);
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