package com.newer.kt.Refactor.ui.Avtivity.FootballLesson;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.frame.app.base.activity.BaseActivity;
import com.frame.app.utils.FileUtil;
import com.frame.app.utils.LogUtils;
import com.frame.app.utils.ZipUtils;
import com.frame.app.view.LoadingDialog;
import com.google.gson.Gson;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.Manager.ListDownManager;
import com.newer.kt.Refactor.Net.CallServer;
import com.newer.kt.Refactor.Net.HttpListener;
import com.newer.kt.Refactor.adapter.CombinationClassAdapter;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.Refactor.ui.Avtivity.Settings.PlayerActivity;
import com.newer.kt.Refactor.utils.Toast;
import com.newer.kt.Refactor.view.CircleProgressView;
import com.newer.kt.Refactor.view.DownListDialog;
import com.newer.kt.entity.BigRoomIdiviBean;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadRequest;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by huangbo on 2016/10/2.
 */
public class BigRoomIndividualActivity extends BaseActivity {
    @Bind(R.id.progress)
    CircleProgressView mProgressView;
    @Bind(R.id.tv_tishi)
    TextView mTishi;
    @Bind(R.id.tv_time)
    TextView mTv_Time;
    @Bind(R.id.tv_cont)
    TextView mTv_cont;
    @Bind(R.id.tv_title)
    TextView mTitle;
    @Bind(R.id.level_name)
    TextView mName;
    @Bind(R.id.tv_manhua_download)
    TextView mTv_manhua_download;
    @Bind(R.id.tv_zhenren_download)
    TextView mTv_zheren_download;
    @Bind(R.id.tv_mKT_download)
    TextView mTv_kt_download;
    @Bind(R.id.tv_chuji)
    TextView mTv_chuji;
    @Bind(R.id.tv_zhongji)
    TextView mTv_zhongji;
    @Bind(R.id.tv_gaoji)
    TextView mTv_gaoji;
    private LoadingDialog mLoadingDialog;
    private BigRoomIdiviBean mBigRoomIdiviBean;
    @Bind(R.id.kt_progress)
    ProgressBar mProgress_kt;
    @Bind(R.id.manhua_progress)
    ProgressBar mProgress_manhua;
    @Bind(R.id.image_kt)
    ImageView mImage_kt;
    @Bind(R.id.image_manhua)
    ImageView mImage_manhua;


    /**
     * 下载任务列表。
     */
    private List<DownloadRequest> mDownloadRequests = new ArrayList<>();

    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_bigroom_individual);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog.show();
        long user_id = PreferenceManager.getDefaultSharedPreferences(getThis()).getLong(LoginActivity.PRE_CURRENT_USER_ID, 0);
        Request<String> request = NoHttp.createStringRequest(Constants.GET_CLASS_DETAIL, RequestMethod.GET);
        request.add("user_id", user_id);
        request.add("app_cartoon_id", getIntent().getStringExtra("app_cartoon_id"));
        request.add("authenticity_token", "K9MpaPMdj0jij2m149sL1a7TcYrWXmg5GLrAJDCNBx8");
        CallServer.getRequestInstance().add(getThis(), 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                if (response != null && response.get() != null && response.get().contains("success")) {
                    Gson gson = new Gson();
                    mBigRoomIdiviBean = gson.fromJson(response.get(), BigRoomIdiviBean.class);
                    mTv_Time.setText(mBigRoomIdiviBean.getFinished_minutes() + "min");
                    mTv_cont.setText(mBigRoomIdiviBean.getFinished_times() + "次");
                    mName.setText(mBigRoomIdiviBean.getNow_level_name());
                    mProgressView.setTimerProgress((int) mBigRoomIdiviBean.getNow_level_progress(), 50);
                    mTv_manhua_download.setText("下载 " + checkSize(mBigRoomIdiviBean.getLessons().get(1).getZip_size()));
                    mTv_kt_download.setText("下载 " + checkSize(mBigRoomIdiviBean.getLessons().get(0).getZip_size()));
                    if (new File(FileUtil.getDownloadDir(getThis()) + getFileName(mBigRoomIdiviBean.getLessons().get(0).getDownload_images_url()).
                            substring(0,getFileName(mBigRoomIdiviBean.getLessons().get(0).getDownload_images_url()).length()-4)).exists()) {
                        mTv_kt_download.setVisibility(View.GONE);
                    } else {
                        mTv_kt_download.setVisibility(View.VISIBLE);
                        mImage_kt.setVisibility(View.GONE);
                    }
                    if (new File(FileUtil.getDownloadDir(getThis()) + getFileName(mBigRoomIdiviBean.getLessons().get(1).getDownload_images_url()).
                            substring(0,getFileName(mBigRoomIdiviBean.getLessons().get(1).getDownload_images_url()).length()-4)).exists()) {
                        mTv_manhua_download.setVisibility(View.GONE);
                    } else {
                        mTv_manhua_download.setVisibility(View.VISIBLE);
                        mImage_manhua.setVisibility(View.GONE);
                    }
                }
                mLoadingDialog.dismiss();
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                mLoadingDialog.dismiss();
            }
        }, false, false);

    }

    private String checkSize(int size) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format((double) size / 1024) + "M";

    }

    @OnClick(R.id.image_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.image_help)
    public void help() {
    }

    @OnClick(R.id.btn_download)
    public void download() {

    }


    @OnClick(R.id.tv_manhua_download)
    public void download_manhua() {
        mTv_manhua_download.setVisibility(View.GONE);
        mProgress_manhua.setVisibility(View.VISIBLE);
        download(mBigRoomIdiviBean.getLessons().get(1).getDownload_images_url(), 0);
    }

    @OnClick(R.id.tv_mKT_download)
    public void download_ky() {
        mTv_kt_download.setVisibility(View.GONE);
        mProgress_kt.setVisibility(View.VISIBLE);
        download(mBigRoomIdiviBean.getLessons().get(0).getDownload_images_url(), 1);
    }

    @OnClick(R.id.linear_zhenren)
    public void download_zhenren() {
        Intent intent = new Intent(BigRoomIndividualActivity.this, ZhenRenVodeoActivity.class);
        intent.putStringArrayListExtra("info", (ArrayList<String>) mBigRoomIdiviBean.getYouku_videos());
        startActivity(intent);

    }

    private void download(final String url, int id) {
        DownloadRequest downloadRequest = NoHttp.createDownloadRequest(getReleaUrl(url), FileUtil.getDownloadDir(this), getFileName(url), true, true);
        mDownloadRequests.add(downloadRequest);
        CallServer.getDownloadInstance().add(id, downloadRequest, new DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                Toast.show(BigRoomIndividualActivity.this, getFileName(url) + "下载失败~请重新下载");
                switch (what) {
                    case 0:
                        mProgress_manhua.setVisibility(View.GONE);
                        mTv_manhua_download.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        mProgress_kt.setVisibility(View.GONE);
                        mTv_kt_download.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

            }

            @Override
            public void onProgress(int what, int progress, long fileCount) {
                switch (what) {
                    case 0:
                        mTv_manhua_download.setVisibility(View.GONE);
                        mProgress_manhua.setVisibility(View.VISIBLE);
                        mProgress_manhua.setProgress(progress);
                        break;
                    case 1:
                        mTv_kt_download.setVisibility(View.GONE);
                        mProgress_kt.setVisibility(View.VISIBLE);
                        mProgress_kt.setProgress(progress);
                        break;
                }
            }

            @Override
            public void onFinish(int what, String filePath) {
                Toast.show(BigRoomIndividualActivity.this, getFileName(url) + "下载完毕~");
                String s = "";
                switch (what) {
                    case 0:
                        mProgress_manhua.setVisibility(View.GONE);
                        mImage_manhua.setVisibility(View.VISIBLE);
                        s = getFileName(mBigRoomIdiviBean.getLessons().get(1).getDownload_images_url());
                        break;
                    case 1:
                        mProgress_kt.setVisibility(View.GONE);
                        mImage_kt.setVisibility(View.VISIBLE);
                        s = getFileName(mBigRoomIdiviBean.getLessons().get(0).getDownload_images_url());
                        break;
                }
                try {
                    File file = new File((FileUtil.getDownloadDir(getThis()) +s.substring(0,s.length()-4)));
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    ZipUtils.UnZipFolder(filePath, (FileUtil.getDownloadDir(getThis()) + s.substring(0,s.length()-4)));
                    new File(filePath).delete();
                } catch (Exception e) {
                    e.printStackTrace();
                    ((BaseActivity) getThis()).showToast("文件解压错误");
                }
            }

            @Override
            public void onCancel(int what) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showDialogToast("下载已取消~");
        CallServer.getDownloadInstance().cancelAll();
    }

    public static String getReleaUrl(String url) {
        try {
            url = URLEncoder.encode(url, "utf-8");
            url = url.replaceAll("\\+", "%20");
            return url.replaceAll("%3A", ":").replaceAll("%2F", "/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    private String getFileName(String url) {
        int index = url.lastIndexOf("/");
        return url.substring(index + 1);
    }

    @OnClick(R.id.image_help)
    public void toHelp() {
        Intent intent = new Intent(getThis(), BigClassHelp.class);
        intent.putExtra("info", mBigRoomIdiviBean);
        startActivity(intent);
    }

    @OnClick(R.id.linear_manhua)
    public void tosayManhua() {
        if (mImage_manhua.getVisibility() == View.VISIBLE) {
            Intent intent = new Intent(this,ImageBrowsActivity.class);
            intent.putExtra("photoAddress",FileUtil.getDownloadDir(getThis()) + getFileName(mBigRoomIdiviBean.getLessons().get(1).getDownload_images_url()).
                    substring(0,getFileName(mBigRoomIdiviBean.getLessons().get(1).getDownload_images_url()).length()-4));
            startActivity(intent);
        }

    }

    @OnClick(R.id.linear_kt)
    public void tosayKt() {
        if (mImage_kt.getVisibility() == View.VISIBLE) {
            Intent intent = new Intent(this,ImageBrowsActivity.class);
            intent.putExtra("photoAddress",FileUtil.getDownloadDir(getThis()) + getFileName(mBigRoomIdiviBean.getLessons().get(0).getDownload_images_url()).
                    substring(0,getFileName(mBigRoomIdiviBean.getLessons().get(0).getDownload_images_url()).length()-4));
            startActivity(intent);
        }
    }

}
