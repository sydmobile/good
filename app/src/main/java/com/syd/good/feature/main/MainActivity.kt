package com.syd.good.feature.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.syd.good.R
import com.syd.good.base.BaseActivity
import com.syd.good.carsonblog.a1socket.SocketClientActivity
import com.syd.good.data.MainContentData
import com.syd.good.feature.a_study.StudyDemoActivity
import com.syd.good.feature.a_template.CommonDemoActivity
import com.syd.good.feature.a_template.SimpleDemoActivity
import com.syd.good.feature.a_test.TestDemoActivity
import com.syd.good.feature.aidl.BinderPoolActivity
import com.syd.good.feature.aidl.IpcActivity
import com.syd.good.feature.animator.*
import com.syd.good.feature.animator.layouttransition.AnimatorLayoutTransitionActivity
import com.syd.good.feature.animator.moveview.ViewMoveAnimateActivity
import com.syd.good.feature.animator.practice.AnimBasePracticeActivity
import com.syd.good.feature.animator.practice.AnimatorBasePracticeActivity
import com.syd.good.feature.animator.viewvisiblegone.CardFlipActivity
import com.syd.good.feature.animator.viewvisiblegone.CardFlipImageActivity
import com.syd.good.feature.animator.viewvisiblegone.CircularRevealActivity
import com.syd.good.feature.animator.viewvisiblegone.CrossfadeActivity
import com.syd.good.feature.customview.simple.CustomViewActivity
import com.syd.good.feature.customview.simple.SimpleViewActivity
import com.syd.good.feature.datastructure.DataStructureActivity
import com.syd.good.feature.dialog.DialogMainActivity
import com.syd.good.feature.dialog.DialogMainStudyActivity
import com.syd.good.feature.dragview.ViewDragHelperTest1Activity
import com.syd.good.feature.dragview.ViewDragHelperTestActivity
import com.syd.good.feature.drawableresource.DrawableResourceActivity
import com.syd.good.feature.ecg.EcgActivity
import com.syd.good.feature.ecg.EcgTestActivity
import com.syd.good.feature.eventbus.EventBusMainActivity
import com.syd.good.feature.fragment.FragmentMainActivity
import com.syd.good.feature.fragment.FragmentStaticActivity
import com.syd.good.feature.fragment.ShowHideActivity
import com.syd.good.feature.imageload.PicassoBaseUseActivity
import com.syd.good.feature.jetpack.MainJetPactActivity
import com.syd.good.feature.language.LanguageActivity
import com.syd.good.feature.mdc.MDCButtonsActivity
import com.syd.good.feature.mdc.MDCMainActivity
import com.syd.good.feature.netutils.NetUtilsActivity
import com.syd.good.feature.officialdocument.AsimpleActivity
import com.syd.good.feature.officialdocument.DrawableActivity
import com.syd.good.feature.officialdocument.MenuActivity
import com.syd.good.feature.recyclerview_study.RecyclerViewBaseActivity
import com.syd.good.feature.recyclerview_study.paging.RecyclerViewLoadingActivity
import com.syd.good.feature.resultapi.ResultApiDemoActivity
import com.syd.good.feature.scroller.ScrollerActivity
import com.syd.good.feature.service.ServiceTestActivity
import com.syd.good.feature.setting.SettingActivity
import com.syd.good.feature.sqlite.SQLiteBaseActivity
import com.syd.good.feature.tabmenu.TabHostActivity
import com.syd.good.feature.test.*
import com.syd.good.feature.viewbinding.ViewBindingActivity
import com.syd.good.feature.webview.WebViewUseActivity
import com.syd.good.feature.xmlparse.JsonParseActivity
import com.syd.good.feature.xmlparse.XmlParseActivity
import com.syd.good.rwx.practicedraw1.Practicedraw1Activity
import com.syd.good.rwx.practicedraw2.PaintDetail1Activity
import com.syd.good.rwx.practicedraw2.PaintDetailActivity
import com.syd.good.ryg.a3customview.ViewShowActivity
import com.syd.good.software.BeaconInfoActivity
import com.syd.good.software.login.LoginByPhoneActivity
import com.syd.good.surfaceView.SurfaceViewActivity


const val TITLE_TYPE = 1
const val COMMON_TYPE = 2

class MainActivity : BaseActivity() {
    override fun layoutId() = R.layout.activity_main

    val datas = listOf(

        // 快速模板页面
        MainContentData("快速测试模板", TITLE_TYPE, null),
        MainContentData("普通页面功能模板", COMMON_TYPE, CommonDemoActivity::class.java),
        MainContentData("简单页面模板", 2, SimpleDemoActivity::class.java),
        MainContentData("测试页面", 2, TestDemoActivity::class.java),
        MainContentData("快速学习页面", COMMON_TYPE, StudyDemoActivity::class.java),

        // 跨进程
        MainContentData("IPC", TITLE_TYPE, CustomViewActivity::class.java),
        MainContentData("IPC", 2, IpcActivity::class.java),
        MainContentData(
            "Socket",
            2,
            com.syd.good.feature.aidl.socket.SocketClientActivity::class.java
        ),
        MainContentData("BinderPool", 2, BinderPoolActivity::class.java),

        // 新知识
        MainContentData("新知识学习", 1, ViewBindingActivity::class.java),
        MainContentData("ViewBinding", 2, ViewBindingActivity::class.java),
        MainContentData("RecyclerView", 2, RecyclerViewBaseActivity::class.java),
        MainContentData("加载下一页", 2, RecyclerViewLoadingActivity::class.java),
        MainContentData("Activity Result API", 2, ResultApiDemoActivity::class.java),
        MainContentData("surfaceview", 2, SurfaceViewActivity::class.java),


        // 第三方框架
        MainContentData("第三方框架", TITLE_TYPE, EventBusMainActivity::class.java),
        MainContentData("EventBus", COMMON_TYPE, EventBusMainActivity::class.java),


        // Material Design
        MainContentData("Material Design", 1, MDCMainActivity::class.java),
        MainContentData("主页面", 2, MDCMainActivity::class.java),
        MainContentData("Button", 2, MDCButtonsActivity::class.java),


        // 小知识
        MainContentData("小知识", 1, ContentBrowserActivity::class.java),
        MainContentData("ContentBrowserActivity", 2, ContentBrowserActivity::class.java),
        MainContentData("VideoPlayerActivity", 2, VideoPlayerActivity::class.java),
        MainContentData("语言设置", 2, LanguageActivity::class.java),
        MainContentData("go系统页面", 2, SettingActivity::class.java),


        // 资源文件
        MainContentData("资源文件知识", 1, MainActivity::class.java),
        MainContentData("layer-list", 2, DrawableResourceActivity::class.java),


        // JetPack 内容
        MainContentData("JetPack", 1, MainActivity::class.java),
        MainContentData("ViewModel", 2, MainJetPactActivity::class.java),


        // hencorder 学习内容
        MainContentData("hencorder", 1, MainActivity::class.java),
        MainContentData("draw练习", 2, Practicedraw1Activity::class.java),
        MainContentData("paint详解", 2, PaintDetailActivity::class.java),
        MainContentData("paint详解", 2, PaintDetail1Activity::class.java),


        // 动画内容
        MainContentData("动画", 1, MainActivity::class.java),
        MainContentData("属性动画", 2, AnimatorActivity::class.java),
        MainContentData("LayoutTransition", 2, AnimatorLayoutTransitionActivity::class.java),
        MainContentData("属性动画练习", 2, AnimatorBasePracticeActivity::class.java),
        MainContentData("视图动画练习", 2, AnimBasePracticeActivity::class.java),

        MainContentData("视图显示或隐藏", 1, MainActivity::class.java),
        MainContentData("淡入淡出视图", 2, CrossfadeActivity::class.java),
        MainContentData("卡片翻转", 2, CardFlipActivity::class.java),
        MainContentData("卡片翻转同一布局", 2, CardFlipImageActivity::class.java),
        MainContentData("揭露动画", 2, CircularRevealActivity::class.java),

        MainContentData("视图移动", 2, ViewMoveAnimateActivity::class.java),

        MainContentData("补间动画", 2, ViewAnimationActivity::class.java),
        MainContentData("插值器", 2, InterpolatorBaseUseActivity::class.java),
        MainContentData("揭露动画", 2, RevealAnimationActivity::class.java),


        // Fragment 相关
        MainContentData("Fragment相关", TITLE_TYPE, MainActivity::class.java),
        MainContentData("Fragment", 2, FragmentMainActivity::class.java),
        MainContentData("Fragment 静态添加", 2, FragmentStaticActivity::class.java),
        MainContentData("Fragment 懒加载", 2, ShowHideActivity::class.java),
        MainContentData("TabHost", 2, com.syd.good.feature.tabhost.TabHostActivity::class.java),
        MainContentData(
            "FragmentTabHost",
            2,
            com.syd.good.feature.tabhost.TabHostFActivity::class.java
        ),
        MainContentData("FragmentTabHost_viewPager", 2, TabHostActivity::class.java),


        // Dialog
        MainContentData("Dialog", 1, DialogMainStudyActivity::class.java),
        MainContentData("Dialog", COMMON_TYPE, DialogMainStudyActivity::class.java),
        MainContentData("Dialog源码", COMMON_TYPE, DialogMainActivity::class.java),


        // View 视图相关
        MainContentData("View视图相关", 1, com.syd.good.feature.tabhost.TabHostFActivity::class.java),
        MainContentData("简单ViewGroup", 2, SimpleViewActivity::class.java),
        MainContentData("简单自定义View", 2, CustomViewActivity::class.java),
        MainContentData("滑动", 2, ScrollerActivity::class.java),
        MainContentData("View拖动", 2, ViewDragHelperTestActivity::class.java),
        MainContentData("View拖动底部菜单", 2, ViewDragHelperTest1Activity::class.java),


        // 自定义 View
        MainContentData("自定义View", 1, EcgActivity::class.java),
        MainContentData("心电图", 2, EcgActivity::class.java),
        MainContentData("局部刷新", 2, EcgTestActivity::class.java),


        // Carson 博客学习
        MainContentData("Carson博客学习", 1, MainActivity::class.java),
        MainContentData("XML解析", 2, XmlParseActivity::class.java),
        MainContentData("Json解析", 2, JsonParseActivity::class.java),
        MainContentData("底部菜单TabHost", 2, TabHostActivity::class.java),
        MainContentData("Picasso", 2, PicassoBaseUseActivity::class.java),
        MainContentData("WebView", 2, WebViewUseActivity::class.java),
        MainContentData("监听网络变化", 2, NetUtilsActivity::class.java),
        MainContentData("前台服务、通知", 2, ServiceTestActivity::class.java),
        MainContentData("数据库操作", 2, SQLiteBaseActivity::class.java),
        MainContentData("Socket", 2, SocketClientActivity::class.java),

        // 官方文档内容
        MainContentData("官方文档", 1, MainActivity::class.java),
        MainContentData("构建首个应用", 2, AsimpleActivity::class.java),
        MainContentData("可绘制对象", 2, DrawableActivity::class.java),
        MainContentData("菜单", 2, MenuActivity::class.java),


        // 任玉刚学习路线
        MainContentData("任玉刚学习路线", 1, MainActivity::class.java),
        MainContentData("01自定义View", 2, ViewShowActivity::class.java),

        // 数据结构
        MainContentData("数据结构",1,MainActivity::class.java),
        MainContentData("队列",2, DataStructureActivity::class.java),

        // 假页面
        MainContentData("软著申请假页面", 1, MainActivity::class.java),
        MainContentData("Beacon信息", 2, BeaconInfoActivity::class.java),
        MainContentData("登录页面", 2, LoginByPhoneActivity::class.java)
    )

    override fun init(savedInstanceState: Bundle?) {
        Log.e(javaClass.simpleName, "$taskId==")
        val recyclerView: RecyclerView = findViewById(R.id.rlv)
        val adapter = MainAdapter()
        recyclerView.adapter = adapter
        val layoutManager = GridLayoutManager(this, 3)
        layoutManager.spanSizeLookup = MySanSizeLookup()
        recyclerView.layoutManager = layoutManager


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    inner class MySanSizeLookup : SpanSizeLookup() {

        override fun getSpanSize(position: Int): Int {
            if (datas[position].type == 1) {
                return 3
            } else {
                return 1
            }
        }

    }


    inner class MainAdapter(private val layoutInflater: LayoutInflater = LayoutInflater.from(this@MainActivity)) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemViewType(position: Int) = datas[position].type

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            return if (viewType == 1) {
                val view = layoutInflater.inflate(R.layout.main_item_title, parent, false)
                TitleViewHolder(view)
            } else {
                val view = layoutInflater.inflate(R.layout.main_item_card, parent, false)
                val viewHolder = ContentViewHolder(view)
                viewHolder.cardView?.setOnClickListener {
                    val intent = Intent(this@MainActivity, datas[viewHolder.adapterPosition].cls)
                    startActivity(intent)
                }
                viewHolder
            }
        }

        override fun getItemCount(): Int {
            return datas.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (datas[position].type == 1) {
                val viewHolder: TitleViewHolder = holder as TitleViewHolder
                viewHolder.tvTitle?.text = datas[position].title
            } else {
                val viewHolder: ContentViewHolder = holder as ContentViewHolder
                viewHolder.tvContent?.text = datas[position].title
            }
        }

    }

    inner class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView? = null

        init {
            tvTitle = itemView.findViewById(R.id.tv_title)
        }
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvContent: TextView? = null
        var cardView: MaterialCardView? = null

        init {
            tvContent = itemView.findViewById(R.id.tv_content)
            cardView = itemView.findViewById(R.id.card_view)
        }

    }

}
