package com.newer.kt.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newer.kt.R;
import com.newer.kt.myClass.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by leo on 16/11/9.
 */
public class TestAdater extends RecyclerView.Adapter<TestAdater.ViewHolder> {
    List<String> mList = new ArrayList<>();

    public TestAdater(List<String> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.mTop.setVisibility(View.VISIBLE);
                holder.mTv_cont.setVisibility(View.INVISIBLE);
                holder.mTop.setImageResource(R.drawable.top_1);
                holder.mHead.setBackgroundResource(R.drawable.yuan_1);
                Glide.with(holder.itemView.getContext()).load("").transform(new GlideCircleTransform(holder.itemView.getContext())).error(R.drawable.result_btnkt).into(holder.mHead);
                break;
            case 1:
                holder.mTop.setVisibility(View.VISIBLE);
                holder.mTv_cont.setVisibility(View.INVISIBLE);
                holder.mTop.setImageResource(R.drawable.top_2);
                holder.mHead.setBackgroundResource(R.drawable.yuan_2);
                Glide.with(holder.itemView.getContext()).load("").transform(new GlideCircleTransform(holder.itemView.getContext())).error(R.drawable.result_btnkt).into(holder.mHead);
                break;
            case 2:
                holder.mTop.setVisibility(View.VISIBLE);
                holder.mTv_cont.setVisibility(View.INVISIBLE);
                holder.mTop.setImageResource(R.drawable.top_3);
                holder.mHead.setBackgroundResource(R.drawable.yuan_3);
                Glide.with(holder.itemView.getContext()).load("").transform(new GlideCircleTransform(holder.itemView.getContext())).error(R.drawable.result_btnkt).into(holder.mHead);
                break;
            default:
                holder.mTop.setVisibility(View.INVISIBLE);
                holder.mTv_cont.setVisibility(View.VISIBLE);
                holder.mTv_cont.setText(position + 1 + "");
                holder.mHead.setBackgroundResource(R.drawable.yuan_4);
                Glide.with(holder.itemView.getContext()).load("").transform(new GlideCircleTransform(holder.itemView.getContext())).error(R.drawable.result_btnkt).into(holder.mHead);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image_top)
        ImageView mTop;
        @Bind(R.id.tv_cont)
        TextView mTv_cont;
        @Bind(R.id.image_head)
        ImageView mHead;
        @Bind(R.id.tv_name)
        TextView mName;
        @Bind(R.id.image_sex)
        ImageView mSex;
        @Bind(R.id.tv_gradle)
        TextView tv_gradle;
        @Bind(R.id.tv_dongzuo)
        TextView tv_dongzuo;
        @Bind(R.id.tv_all_cont)
        TextView tv_all_cont;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
