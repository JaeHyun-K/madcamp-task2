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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NavigationTwo extends Fragment {
    @Nullable

    RecyclerView mRecyclerView;
    private Toolbar toolbar;

    public static File binarytoFile(String binaryFile, String filePath, String fileName) {
        if ((binaryFile == null || "".equals(binaryFile)) || (filePath == null || "".equals(filePath))
                || (fileName == null || "".equals(fileName))) {
            return null;
        }

        FileOutputStream fos = null;

        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        File destFile = new File(filePath + fileName);

        byte[] buff = binaryFile.getBytes();
        String toStr = new String(buff);
        byte[] b64dec = base64Dec(toStr);

        try {
            fos = new FileOutputStream(destFile);
            fos.write(b64dec);
            fos.close();
        } catch (IOException e) {
            System.out.println("Exception position : FileUtil - binaryToFile(String binaryFile, String filePath, String fileName)");
        }

        return destFile;
    }

    private final String mImageText[] = {};
    private final Integer mImageList[] = {};

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