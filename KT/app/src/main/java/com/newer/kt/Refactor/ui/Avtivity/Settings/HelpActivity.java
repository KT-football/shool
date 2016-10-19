package com.newer.kt.Refactor.ui.Avtivity.Settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;

import com.frame.app.base.activity.BaseToolBarActivity2;
import com.newer.kt.R;

/**
 * Created by jy on 16/8/23.
 */
public class HelpActivity extends BaseToolBarActivity2 {

    private LinearLayout ll;

    @Override
    protected void initToolBar() {
        setToolBarTitle("帮助");
    }

    @Override
    protected void OnNavigationClick(View v) {
        finish();
    }

    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    protected void setListener() {
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent("android.intent.action.CALL",
                        Uri.parse("tel:" + "4000062666"));
                //启动
                startActivity(phoneIntent);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        addContentView(R.layout.layout_help);
        setBackgroundResource(R.drawable.judge_background);
        ll = (LinearLayout) findViewById(R.id.layout_help_item_1);
    }
}
