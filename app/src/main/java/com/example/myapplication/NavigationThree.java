package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import android.view.ViewGroup;
import android.view.View;

public class NavigationThree extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View navigation_tab3 = inflater.inflate(R.layout.navigation_tab3, container, false);
        return navigation_tab3;
    }
}