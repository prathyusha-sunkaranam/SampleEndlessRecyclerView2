package com.example.mansopresk21.sampleendlessrecyclerview;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.commit451.endlessrecyclerviewonscrolllistener.EndlessRecyclerViewOnScrollListener;
import com.example.mansopresk21.sampleendlessrecyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mStringList;
    private int mLoadedItems = 0;
    private SampleAdapter mSampleAdapter;
    private ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mActivityMainBinding.recyclerView.setLayoutManager(linearLayoutManager);
        mActivityMainBinding.recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        mStringList = new ArrayList<>();
        mSampleAdapter = new SampleAdapter(mStringList);
        mActivityMainBinding.recyclerView.setAdapter(mSampleAdapter);
        addDataToList();

        mActivityMainBinding.recyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                addDataToList();
            }
        });
    }
    private void addDataToList() {
        mActivityMainBinding.itemProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 30; i++) {
                    mStringList.add("SampleText : " + mLoadedItems);
                    mLoadedItems++;
                }
                mSampleAdapter.notifyDataSetChanged();
                mActivityMainBinding.itemProgressBar.setVisibility(View.GONE);
            }
        }, 1500);

    }
}


