package com.syd.good.feature.xmlparse;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.feature.common.CommonAdapter;
import com.syd.good.feature.common.CommonEntity;
import com.syd.good.feature.common.CommonType;
import com.syd.good.utils.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.BindView;

/**
 * <p>
 * date: 2020/9/3 8:59
 * json 解析
 *
 * @author syd
 * @version 1.0
 */
public class JsonParseActivity extends BaseActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;

    String json = "{\n" +
            "  \"students\": [\n" +
            "    {\"id\": 1,\"name\": \"小明\"},\n" +
            "    {\"id\": 2,\"name\": \"小华\"},\n" +
            "    {\"id\": 3,\"name\": \"小李\"}\n" +
            "  ],\n" +
            "  \"class\": \"一班\"\n" +
            "}";
    @Override
    protected int layoutId() {
        return R.layout.common_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // 标准化流程
        List<CommonEntity> datas = new ArrayList<>();
        datas.add(new CommonEntity("标题", "Json解析", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("标题", "SAX解析", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("标题", "PULL解析", CommonType.TYPE_CONTENT_COMMON, null));
        CommonAdapter.CallBack callBack = new CommonAdapter.CallBack() {
            @Override
            public void onClick(CommonEntity commonEntity) {

                switch (commonEntity.getmContent()) {
                    case "Json解析":
                        parseJson();
                        break;
                    case "SAX解析":

                        break;
                    case "PULL解析":

                        break;
                    default:
                }
            }
        };
        actionInit(datas, callBack);


    }


    /**
     * 自带 org.json解析
     */
    public void parseJson(){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("students");
            for (int i = 0;i<array.length();i++){
               JSONObject jsonObject1 =  array.getJSONObject(i);
               L.e("json",jsonObject1.toString());
               L.e("id","="+jsonObject1.getInt("id"));
               L.e("name","="+jsonObject1.getString("name"));
            }
            L.e("class",jsonObject.getString("class"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 基本内容
     *
     * @param datas    数据
     * @param callBack 回调
     */
    private void actionInit(List<CommonEntity> datas, CommonAdapter.CallBack callBack) {
        CommonAdapter adapter = new CommonAdapter(this, callBack);
        adapter.setDatas(datas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getDatas().get(position).getmType()) {
                    case CommonType.TYPE_TITLE:
                        return 4;
                    case CommonType.TYPE_CONTENT1:
                        return 2;
                    default:
                        return 1;

                }
            }
        });
        rlv.setLayoutManager(gridLayoutManager);
        rlv.setAdapter(adapter);
    }

    static class MyHandler extends DefaultHandler {

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            L.e("startDocument");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            L.e("startElement", uri + "==" + localName + "==" + qName + "==" + attributes.toString());
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            L.e("characters", new String(ch, 0, ch.length) + "==" + start + "==" + length + ch);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            L.e("endElement", uri + "==" + localName + "==" + qName + "==");
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            L.e("endDocument");
        }
    }
}
