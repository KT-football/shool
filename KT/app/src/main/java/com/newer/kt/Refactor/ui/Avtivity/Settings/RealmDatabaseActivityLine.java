package com.newer.kt.Refactor.ui.Avtivity.Settings;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.frame.app.utils.GsonTools;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.realm.implementation.RealmLineData;
import com.github.mikephil.charting.data.realm.implementation.RealmLineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.newer.kt.R;
import com.newer.kt.Refactor.Base.RealmBaseActivity;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.Custom.RealmDemoData;
import com.newer.kt.Refactor.Entitiy.BattlesStatistics;
import com.newer.kt.Refactor.Entitiy.BattlesStatisticsBean;
import com.newer.kt.Refactor.Entitiy.FinishedCount;
import com.newer.kt.Refactor.Entitiy.FinishedCountBean;
import com.newer.kt.Refactor.KTApplication;
import com.newer.kt.Refactor.ui.Avtivity.ListActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Philipp Jahoda on 21/10/15.
 */
public class RealmDatabaseActivityLine extends RealmBaseActivity {

    private LineChart mChart;
    private List<FinishedCountBean> list;
    private TextView bt;
    private int code;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        addContentView(R.layout.activity_linechart_noseekbar);
        setBackgroundResource(R.drawable.judge_background);
        bt = (TextView) findViewById(R.id.icon_char_bt);

        mChart = (LineChart) findViewById(R.id.chart1);
        setup(mChart);

        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getXAxis().setDrawGridLines(false);
    }

    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    protected void setListener() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (code) {
                    case 1:
                        startActivity(PhysicalEducationListActivity.class);
                        break;
                    case 2:
                        startActivity(BigRoomListActivity.class);
                        break;
                    case 3:
                        Intent intent = new Intent(getThis(), ListActivity.class);
                        intent.putExtra(Constants.EXTRA_LIST_CODE, 3);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        code = getIntent().getIntExtra(Constants.REALMDATABASEACTIVITYLINE_TITLE, 1);
        String title = "";
        switch (code) {
            case 1:
                title = "体育课统计";
                FinishedCount ss1 = GsonTools.changeGsonToBean(KTApplication.getGymCourseRecordsStatistics(), FinishedCount.class);
                list = ss1.list;
                break;
            case 2:
                title = "大课间统计";
                FinishedCount ss2 = GsonTools.changeGsonToBean(KTApplication.getBigClassroomRecordsStatistics(), FinishedCount.class);
                list = ss2.list;
                break;
            case 3:
                title = "赛事统计";
                BattlesStatistics ss3 = GsonTools.changeGsonToBean(KTApplication.getBattlesStatistics(), BattlesStatistics.class);
                list = new ArrayList<>();
                for (int x = 0; x < ss3.list.size(); x++) {
                    BattlesStatisticsBean bean = ss3.list.get(x);
                    FinishedCountBean finishedCountBean = new FinishedCountBean();
                    finishedCountBean.date = bean.date;
                    finishedCountBean.finished_count = bean.battles_count;
                    list.add(finishedCountBean);
                }
                break;
        }
        setToolBarTitle(title);
    }

    @Override
    public void onResume() {
        super.onResume(); // setup realm

        // write some demo-data into the realm.io database
        writeToDB();

        // add data to the chart
        setData();
    }

    private void setData() {

        RealmResults<RealmDemoData> result = mRealm.allObjects(RealmDemoData.class);

        RealmLineDataSet<RealmDemoData> set = new RealmLineDataSet<RealmDemoData>(result, "value", "xIndex");
//        set.setDrawCubic(false);
        set.setLabel("次数");
//        set.setDrawCircleHole(false);
        set.setColor(getResourcesColor(R.color.gold));
        set.setCircleColor(getResourcesColor(R.color.gold));
        set.setHighLightColor(Color.WHITE);
        set.setLineWidth(1.8f);
        set.setCircleSize(3.6f);
        set.setValueTextColor(Color.WHITE);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set); // add the dataset

        // create a data object with the dataset list
        RealmLineData data = new RealmLineData(result, "xValue", dataSets);
        styleData(data);

        // set data
        mChart.setData(data);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuart);
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void OnNavigationClick(View v) {
        finish();
    }

    protected void writeToDB() {

        mRealm.beginTransaction();

        mRealm.clear(RealmDemoData.class);

        for (int i = 0; i < list.size(); i++) {
            FinishedCountBean finishedCountBean = list.get(i);
            RealmDemoData d = new RealmDemoData(finishedCountBean.finished_count, i, finishedCountBean.date);
            mRealm.copyToRealm(d);
        }

        mRealm.commitTransaction();
    }
}
