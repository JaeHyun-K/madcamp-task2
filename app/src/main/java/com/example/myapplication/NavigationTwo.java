package com.example.myapplication;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import android.view.ViewGroup;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

public class NavigationTwo extends Fragment {
    @Nullable

    RecyclerView mRecyclerView;
    private Toolbar toolbar;


    private final String mImageText[] = {"폰배경화면","sleepy 쭈","산책시러","꺄~뀽~!","zzz","!!!","킁킁","꺠꼬닥","꾸에에엥","꾸?","아잉 졸려","후에에엥","찌찌자랑", "방석킬러","사이좋은고양이들","사이좋은고양이들2","아이러브캣타워",};
    private final Integer mImageList[] = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
            R.drawable.image_4, R.drawable.image_5, R.drawable.image_6, R.drawable.image_7,
            R.drawable.image_8, R.drawable.image_9, R.drawable.image_10, R.drawable.image_11,
            R.drawable.image_12, R.drawable.image_13, R.drawable.image_14, R.drawable.image_15, R.drawable.image_16, R.drawable.image_17,};

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View navigation_gallery = inflater.inflate(R.layout.navigation_gallery, container, false);

        mRecyclerView = navigation_gallery.findViewById(R.id.image);
        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        toolbar = (Toolbar) navigation_gallery.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Gallery");


        ArrayList<NavigationTwo_CreateItem> createLists = prepareData();

        NavigationTwo_RecyclerViewAdapter myAdapter = new NavigationTwo_RecyclerViewAdapter(getActivity(), createLists);
        mRecyclerView.setAdapter(myAdapter);

        return navigation_gallery;
    }

    private ArrayList<NavigationTwo_CreateItem> prepareData(){

        ArrayList<NavigationTwo_CreateItem> theimage = new ArrayList<>();
        for(int i = 0; i< mImageText.length; i++){
            NavigationTwo_CreateItem createList = new NavigationTwo_CreateItem();
            createList.setImage_title(mImageText[i]);
            createList.setImage_ID(mImageList[i]);
            theimage.add(createList);
        }
        return theimage;
    }


}