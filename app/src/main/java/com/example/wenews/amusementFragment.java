package com.example.wenews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angel.R;

import java.util.ArrayList;
import java.util.List;

public class amusementFragment extends channelFragment {
    private List<NewItem> newItems=new ArrayList<NewItem>();
    private RecyclerView recyclerView_amusement;
    private  NewsAdapter adapter;

    public amusementFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_amusement, container, false);
        recyclerView_amusement=(RecyclerView) view.findViewById(R.id.recyclerview_amusement) ;
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView_amusement.setLayoutManager(layoutManager);
        adapter=new NewsAdapter(newItems);
        recyclerView_amusement.setAdapter(adapter);
        address="";

        GetNews();

        return view;
    }



}
