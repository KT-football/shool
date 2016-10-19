package com.newer.kt.event;

/**
 * Created by leo on 16/10/11.
 */

public class MainEvent {
    /**
     * 1.修改用戶信息
     */
    private  int mType;

    public MainEvent(int mType) {
        this.mType = mType;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }
}
