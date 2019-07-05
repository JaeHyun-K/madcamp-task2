package com.example.myapplication;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class NavigationTwo_Detail extends AppCompatActivity implements OnClickListener {
    ImageView mImage;
    Button btn_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_gallery_detailitem);

        mImage = findViewById(R.id.image);
        Bundle mBundle = getIntent().getExtras();
        if(mBundle != null){
            mImage.setImageResource(mBundle.getInt("Image"));
        }

        btn_intent = (Button)findViewById(R.id.btn_intent);
        btn_intent.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        finish();
    }
}
