package com.newer.kt.fragment.peixun;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import com.frame.app.base.fragment.BaseFragment;
import com.newer.kt.R;
import com.newer.kt.Refactor.view.LoadMoreRecyclerView;
import com.newer.kt.adapter.BigClassAdater;
import com.newer.kt.adapter.TestAdater;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by leo on 16/11/9.
 */

public class TestFragment extends BaseFragment implements LoadMoreRecyclerView.LoadMoreListener {
    @Bind(R.id.tv_all_people)
    TextView mAll_people;
    @Bind(R.id.tv_all)
    TextView mAll;
    @Bind(R.id.tv_pingjun)
    TextView mAll_pingjun;
    @Bind(R.id.tv_all_nale)
    TextView tv_all_nale;
    @Bind(R.id.recyclerview)
    LoadMoreRecyclerView mRecyclerView;
    private Typeface mTf;
    @Bind(R.id.image_nomal)
    ImageView mImageView;
    List<String> mList = new ArrayList<>();
    @Bind(R.id.appbar)
    AppBarLayout appbar;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTf = Typeface.createFromAsset(getActivity().getAssets(), "BEBAS.ttf");
        mAll.setTypeface(mTf);
        mAll_pingjun.setTypeface(mTf);
        mAll_people.setTypeface(mTf);
        mRecyclerView.setLoadMoreListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mRecyclerView.setAdapter(new TestAdater(mList));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appbar.setOutlineProvider(null);
        }
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void initHandler(Message msg) {

    }

    @OnClick(R.id.tv_start)
    public void start() {

    }

    @Override
    public void onLoadMore() {
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mList.add("dasdasdas");
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
