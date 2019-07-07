package com.example.myapplication;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;
import android.view.View;
import android.widget.CalendarView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NavigationThree extends Fragment {

    private FloatingActionButton fab;
    private RecyclerView m2Recyclerview;
    private RecyclerAdapter adapter2;
    private Toolbar toolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View navigation_tab3 = inflater.inflate(R.layout.navigation_tab3, container, false);

        fab = (FloatingActionButton)((AppCompatActivity)getActivity()).findViewById(R.id.plus);
        fab.setOnClickListener(new view.onclickli);


//        mCalendarView = (CalendarView)((AppCompatActivity)getActivity()).findViewById(R.id.calender);
//
//        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
//                String date = i+"/"+
//            }
        });
        return navigation_tab3;
    }
}