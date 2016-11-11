package com.newer.kt.fragment.peixun;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.frame.app.base.fragment.BaseFragment;
import com.newer.kt.R;
import com.newer.kt.adapter.BigClassAdater;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by leo on 16/11/9.
 */

public class BigClassFragment extends BaseFragment {
    @Bind(R.id.tv_all_class)
    TextView mAll_class;
    @Bind(R.id.tv_all_people)
    TextView mAll_people;
    @Bind(R.id.tv_all)
    TextView mAll;
    @Bind(R.id.tv_pingjun)
    TextView mAll_pingjun;
    @Bind(R.id.tv_all_nale)
    TextView tv_all_nale;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private Typeface mTf;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_big_class);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTf = Typeface.createFromAsset(getActivity().getAssets(), "BEBAS.ttf");
        mAll.setTypeface(mTf);
        mAll_pingjun.setTypeface(mTf);
        mAll_class.setTypeface(mTf);
        mAll_people.setTypeface(mTf);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<String> mList = new ArrayList<>();
        mList.add("dadasdas");
        mList.add("dadasdas");
        mList.add("dadasdas");
        mList.add("dadasdas");
        mList.add("dadasdas");
        mList.add("dadasdas");
        mList.add("dadasdas");
        mList.add("dadasdas");
        mRecyclerView.setAdapter(new BigClassAdater(getActivity(),mList));

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void initHandler(Message msg) {

    }
}
