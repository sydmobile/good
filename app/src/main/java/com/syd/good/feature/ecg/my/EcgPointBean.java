package com.syd.good.feature.ecg.my;

import java.io.Serializable;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/12/16 09:40
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class EcgPointBean implements Serializable {

    public float LEAD_I;

    public float LEAD_II;

    public float LEAD_III;

    public float LEAD_aVR;

    public float LEAD_aVL;

    public float LEAD_aVF;
    public float LEAD_V1;

    public EcgPointBean() {
    }
}