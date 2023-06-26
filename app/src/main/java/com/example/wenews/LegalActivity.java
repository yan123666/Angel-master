package com.example.wenews;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.angel.R;
import com.google.android.material.tabs.TabLayout;
import com.example.chat.Chat;

import java.util.ArrayList;
import java.util.List;

public class LegalActivity extends AppCompatActivity {

    ViewPager viewPager;
    static List<TextView> tag;
    List<Fragment> viewList;
    static   int tagPointer=0;
    static  ProgressDialog  progressDialog ;
    TabLayout tabLayout;
    private SwipeRefreshLayout mSwipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal);


        Button button = findViewById(R.id.button123);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LegalActivity.this,Chat.class);
                startActivity(intent);
            }
        });


        progressDialog=new ProgressDialog(LegalActivity.this);
        progressDialog.setMessage("正在加载内容...");
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mSwipeLayout=(SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        viewList = new ArrayList<Fragment>();// 将要分页显示的View装入数组中
        final  amusementFragment fragment1=new amusementFragment();
        final  financeFragment  fragment2=new financeFragment();
        final  armyFragment fragment3=new armyFragment();
        final  headlineFragment fragment4=new headlineFragment();
        final  InternationalFragment fragment5=new InternationalFragment();
        final  newsFragment fragment6=new newsFragment();


        viewList.add(fragment2);
        viewList.add(fragment3);
        viewList.add(fragment1);
        viewList.add(fragment5);
        viewList.add(fragment4);
        viewList.add(fragment6);


        FragmentManager fragmentManager=getSupportFragmentManager();
        channelPager pagerAdapter=new channelPager(fragmentManager,viewList,this);
        viewPager.setAdapter(pagerAdapter);

        //设置加载动画背景颜色
        mSwipeLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(android.R.color.background_light));
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            public void onRefresh() {
                switch (tagPointer){
                  case 0:
                      fragment1.GetNews();
                      break;
                  case 1:
                      fragment2.GetNews();
                      break;
                  case 2:
                      fragment3.GetNews();
                      break;
                  case 3:
                      fragment4.GetNews();
                      break;
                  case 4:
                      fragment5.GetNews();
                      break;
                  case 5:
                      break;
              }
              mSwipeLayout.setRefreshing(false);
            }
        });
    }

}


