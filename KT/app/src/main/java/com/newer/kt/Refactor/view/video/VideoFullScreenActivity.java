package com.newer.kt.Refactor.view.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Constructor;

/**
 * Created by zhangyunjie
 * On 2016/07/24
 */
public class VideoFullScreenActivity extends Activity {

    static void startActivityFromNormal(Context context, int state, String url, Class videoPlayClass, Object... obj) {
        CURRENT_STATE = state;
        DIRECT_FULLSCREEN = false;
        URL = url;
        VIDEO_PLAYER_CLASS = videoPlayClass;
        OBJECTS = obj;
        Intent intent = new Intent(context, VideoFullScreenActivity.class);
        context.startActivity(intent);
    }

    /**
     * <p>直接进入全屏播放</p>
     * <p>Full screen play video derictly</p>
     *
     * @param context        context
     * @param url            video mUrl
     * @param videoPlayClass your videoplayer extends JCAbstraceVideoPlayer
     * @param obj            custom param
     */
    public static void startActivity(Context context, String url, Class videoPlayClass, Object... obj) {
        CURRENT_STATE = VideoPlayer.CURRENT_STATE_NORMAL;
        URL = url;
        DIRECT_FULLSCREEN = true;
        VIDEO_PLAYER_CLASS = videoPlayClass;
        OBJECTS = obj;
        Intent intent = new Intent(context, VideoFullScreenActivity.class);
        context.startActivity(intent);
    }

    private VideoPlayer mJcVideoPlayer;
    /**
     * 刚启动全屏时的播放状态
     */
    static int CURRENT_STATE = -1;
    public static String URL;
    static boolean DIRECT_FULLSCREEN = false;//this is should be in videoplayer
    static Class VIDEO_PLAYER_CLASS;
    static Object[] OBJECTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        try {
            Constructor<VideoPlayerStandard> constructor = VIDEO_PLAYER_CLASS.getConstructor(Context.class);
            mJcVideoPlayer = constructor.newInstance(this);
            setContentView(mJcVideoPlayer);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mJcVideoPlayer.mIfCurrentIsFullscreen = true;
        mJcVideoPlayer.mIfFullscreenIsDirectly = DIRECT_FULLSCREEN;
        mJcVideoPlayer.setUp(URL, OBJECTS);
        mJcVideoPlayer.setStateAndUi(CURRENT_STATE);
        mJcVideoPlayer.addTextureView();
        if (mJcVideoPlayer.mIfFullscreenIsDirectly) {
            mJcVideoPlayer.startButton.performClick();
        } else {
            VideoPlayer.IF_RELEASE_WHEN_ON_PAUSE = true;
            VideoMediaManager.instance().setListener(mJcVideoPlayer);
            if (CURRENT_STATE == VideoPlayer.CURRENT_STATE_PAUSE) {
                VideoMediaManager.instance().mediaPlayer.seekTo(VideoMediaManager.instance().mediaPlayer.getCurrentPosition());
            }
        }
    }

    @Override
    public void onBackPressed() {
        mJcVideoPlayer.backFullscreen();
    }

    @Override
    protected void onPause() {
        super.onPause();
        VideoPlayer.releaseAllVideos();
//        finish();
    }
}
