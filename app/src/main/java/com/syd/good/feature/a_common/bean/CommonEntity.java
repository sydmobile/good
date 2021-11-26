package com.syd.good.feature.a_common.bean;

/**
 * <p>
 * date: 2020/9/3 9:08
 * 普通的功能项
 * @author syd
 * @version 1.0
 */
public class CommonEntity {
    private String mTitle;
    private String mContent;
    private int mType;
    private Class mCls;

    public CommonEntity(String mTitle, String mContent, int mType, Class mCls) {
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mType = mType;
        this.mCls = mCls;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public Class getmCls() {
        return mCls;
    }

    public void setmCls(Class mCls) {
        this.mCls = mCls;
    }
}
