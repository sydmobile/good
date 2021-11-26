package com.syd.good.feature.webview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.utils.L;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * date: 2020/9/3 8:59
 * 普通通用的 Activity
 *
 * @author syd
 * @version 1.0
 */
public class WebViewUseActivity extends BaseActivity {


    @BindView(R.id.bt_)
    Button bt;
    @BindView(R.id.wv)
    WebView wv;

    @Override
    protected int layoutId() {
        return R.layout.webview_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        bt.setOnClickListener(v -> {
            baseUse();
        });
    }


    @SuppressLint("SetJavaScriptEnabled")
    public void baseUse() {


        // 声明 WebSetting
        WebSettings webSettings = wv.getSettings();
        // 与 JavaScript 交互
        webSettings.setJavaScriptEnabled(true);

        // 设置自适应屏幕
        // 将图片调整到适合 webView 的大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);

        wv.loadUrl("https://www.baidu.com");

        wv.setWebViewClient(new WebViewClient(){

            // 当在网页上点击某个链接的时候，会调用此方法

            /**
             * 如果没有设置 WebViewClient
             * webview 会询问 Activity管理器用合适的方式打开 url
             * 设置了，如果 return true 就打断，return false 继续
             * @param view
             * @param request
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                L.e(TAG,"shouldOverrideUrlLoading:"+request.getUrl());
                return super.shouldOverrideUrlLoading(view, request);
            }

            /**
             * 开始载入页面调用，可以设定一个 loading，告诉用户程序在等待网络响应
             * @param view
             * @param url
             * @param favicon
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                L.e(TAG,"onPageStarted:"+url);
            }

            /**
             * 页面加载结束
             * @param view
             * @param url
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                L.e(TAG,"onPageFinished"+url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
//                L.e(TAG,"onLoadResource"+url);
           }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                L.e(TAG,"onReceivedError:"+error.getDescription());
            }

        });

        wv.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                L.e(TAG,"newProgress:"+newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                L.e(TAG,"onReceivedTitle:"+title);
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }
}
