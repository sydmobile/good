package com.syd.good.feature.xmlparse;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.feature.common.CommonAdapter;
import com.syd.good.feature.common.CommonEntity;
import com.syd.good.feature.common.CommonType;
import com.syd.good.utils.L;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
 * xml 解析
 *
 * @author syd
 * @version 1.0
 */
public class XmlParseActivity extends BaseActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;

    @Override
    protected int layoutId() {
        return R.layout.common_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.e(TAG, "onDestroy");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // 标准化流程
        List<CommonEntity> datas = new ArrayList<>();
        datas.add(new CommonEntity("标题", "DOM解析", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("标题", "SAX解析", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("标题", "PULL解析", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("标题", "添加Window", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("标题", "显示Dialog", CommonType.TYPE_CONTENT_COMMON, null));
        CommonAdapter.CallBack callBack = new CommonAdapter.CallBack() {
            @Override
            public void onClick(CommonEntity commonEntity) {

                switch (commonEntity.getmContent()) {
                    case "DOM解析":
                        parseXmlByDom();
                        break;
                    case "SAX解析":
                        parseXmlBySax();
                        break;
                    case "PULL解析":
                        parseXmlByPull();
                        break;
                    case "添加Window":
                        TextView tv = new TextView(XmlParseActivity.this);
                        tv.setText("woshixxx");
                        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                        layoutParams.height = 1000;
                        layoutParams.width = 200;
                        getWindowManager().addView(tv,layoutParams);
                        break;
                    case "显示Dialog":
                        Dialog dialog = new Dialog(XmlParseActivity.this);
                        dialog.setContentView(R.layout.common_dialog_remind);
                        dialog.show();
                    default:
                }
            }
        };
        actionInit(datas, callBack);


    }

    /**
     * DOM 解析
     */
    public void parseXmlByDom() {
        try {
            // 打开 xml 文件到输入流
            InputStream stream = getAssets().open("subject.xml");
            // 得到 DocumentBuilderFactory 对象
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // 得到 DocumentBuilder 对象
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            // 得到 Document
            Document document = documentBuilder.parse(stream);
            // 得到 xml 数据根节点
            Element element = document.getDocumentElement();
            // 获取根节点的所有字节点
            NodeList list = element.getElementsByTagName("language");
            // 遍历所有节点
            for (int i = 0; i < list.getLength(); i++) {
                Element language = (Element) list.item(i);
                L.e("id", language.getAttribute("id"));
                L.e("name", language.getElementsByTagName("name").item(0).getTextContent());
                L.e("usage", language.getElementsByTagName("usage").item(0).getTextContent());
            }



        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }


    public void parseXmlBySax() {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();

            XMLReader reader = saxParser.getXMLReader();
            MyHandler handler = new MyHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(getAssets().open("subject.xml")));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    public void parseXmlByPull() {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(getAssets().open("subject.xml")));
            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        switch (nodeName) {
                            case "language":

                                break;
                            case "name":
                                L.e("name", "==" + xmlPullParser.nextText());
                                break;
                            default:
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        L.e("xxx", eventType + "");
                        break;
                    default:
                        break;
                }
                xmlPullParser.next();
            }

        } catch (XmlPullParserException | IOException e) {
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
