package com.syd.good.feature.ecg.my;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/10/27 14:46
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class EcgConfigBean {
    /** 轨道的顺序信息 */
    private String[] trackInfo;
    
    /** 走纸速度 表现：ms / mm */
    private float mMsPerMM;
    /** 增益  vm / mm */
    private float mVmPerMM;
    /** 持续时间 */
    private int duration;

    private String user_id;

    private String img;
    /** 采样率 */
    private int rate;

    public String[] getTrackInfo() {
        return trackInfo;
    }

    public void setTrackInfo(String[] trackInfo) {
        this.trackInfo = trackInfo;
    }

    public float getMsPerMM() {
        return mMsPerMM;
    }

    public void setMsPerMM(float msPerMM) {
        mMsPerMM = msPerMM;
    }

    public float getVmPerMM() {
        return mVmPerMM;
    }

    public void setVmPerMM(float vmPerMM) {
        mVmPerMM = vmPerMM;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
