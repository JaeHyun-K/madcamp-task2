package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import java.lang.Object;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout;
import android.view.ViewParent;
import android.view.ViewManager;
import androidx.viewpager.widget.PagerAdapter;
//import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.*;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import java.util.ArrayList;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.app.Fragment;
public class TabActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ArrayList<conlist> mArrayList;

    private FragmentManager fragmentManager;

    ViewPager viewPager;
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tab);

        //전 Activity에서 정보 넘겨받기
        Intent intent=getIntent();
        String namedata=intent.getStringExtra("namedata");
        //String iddata=intent.getStringExtra("IDdata");


        //android studio 에서 node js로 해당 값 넘겨줘서 연결시켜야함!

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager = findViewById(R.id.viewpager); //Init Viewpager
        setupFm(getSupportFragmentManager(), viewPager); //Setup Fragment
        viewPager.setCurrentItem(0); //Set Currrent Item When Activity Start
        viewPager.addOnPageChangeListener(new PageChange()); //Listeners For Viewpager When Page Changed
    }
    public static void setupFm(FragmentManager fragmentManager, ViewPager viewPager){
        FragmentAdapter Adapter = new FragmentAdapter(fragmentManager);
        //Add All Fragment To List
        Adapter.add(new NavigationOne(), "1");
        Adapter.add(new NavigationTwo(), "2");
        Adapter.add(new NavigationThree(), "3");
        viewPager.setAdapter(Adapter);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_contact:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_gallery:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_flashcards:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };
    public class PageChange implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    setTitle("Contact");
                    navigation.setSelectedItemId(R.id.navigation_contact);
                    break;
                case 1:
                    setTitle("Gallery");
                    navigation.setSelectedItemId(R.id.navigation_gallery);
                    break;
                case 2:
                    setTitle("TODO list");
                    navigation.setSelectedItemId(R.id.navigation_flashcards);
                    break;
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
