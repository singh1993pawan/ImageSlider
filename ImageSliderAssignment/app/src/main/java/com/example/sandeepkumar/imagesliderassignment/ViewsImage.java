package com.example.sandeepkumar.imagesliderassignment;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewsImage extends AppCompatActivity {

    CustomeAdapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views_image);

        viewPager = (ViewPager) findViewById(R.id.image);
        adapter = new CustomeAdapter(this);
        viewPager.setAdapter(adapter);
    }

}
