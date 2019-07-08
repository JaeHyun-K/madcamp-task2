package com.example.myapplication;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;
import android.view.View;
import android.widget.CalendarView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NavigationThree extends Fragment {

    private FloatingActionButton fab;
    private RecyclerView m2Recyclerview;
    private LinearLayoutManager m2LayoutManager;
    private RecyclerAdapter adapter2;
    private Toolbar toolbar;
    private Contactlist data2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View navigation_tab3 = inflater.inflate(R.layout.navigation_tab3, container, false);


        fab = (FloatingActionButton)navigation_tab3.findViewById(R.id.plus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new CalendarActivity()).commit();
            }
        });
        RecyclerView recyclerView3=(RecyclerView)navigation_tab3.findViewById(R.id.recyclerview);
        m2LayoutManager=new LinearLayoutManager(getActivity());
        recyclerView3.setLayoutManager(m2LayoutManager);
//        adapter2=new RecyclerAdapter();
//        recyclerView3.setAdapter(adapter2);



        return navigation_tab3;
    }
}