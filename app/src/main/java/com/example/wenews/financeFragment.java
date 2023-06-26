package com.example.wenews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angel.R;

public class financeFragment extends channelFragment{

    private RecyclerView recyclerView_finance;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_finance, container, false);
        recyclerView_finance=(RecyclerView) view.findViewById(R.id.recyclerview_finace) ;
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView_finance.setLayoutManager(layoutManager);
        adapter=new NewsAdapter(newItems);
        address="http://is.snssdk.com/api/news/feed/v51/";
        recyclerView_finance.setAdapter(adapter);
        GetNews();
        if(!newItems.isEmpty())
            recyclerView_finance.scrollToPosition(0);
        return view;
    }
}
