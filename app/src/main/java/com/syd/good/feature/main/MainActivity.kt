package com.syd.good.feature.main

import android.app.Service
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
import com.syd.good.ConstraintLayoutActivity
import com.syd.good.R
import com.syd.good.base.BaseActivity
import com.syd.good.carsonblog.a1socket.SocketClientActivity
import com.syd.good.carsonblog.a2eventdispatcher.EventDispatcherActivity
import com.syd.good.data.MainContentData
import com.syd.good.feature.animator.*
import com.syd.good.feature.dialog.DialogMainStudyActivity
import com.syd.good.feature.drawableresource.DrawableResourceActivity
import com.syd.good.feature.eventbus.EventBusMainActivity
import com.syd.good.feature.fragment.FragmentMainActivity
import com.syd.good.feature.fragment.FragmentStaticActivity
import com.syd.good.feature.imageload.PicassoBaseUseActivity
import com.syd.good.feature.mdc.MDCButtonsActivity
import com.syd.good.feature.mdc.MDCMainActivity
import com.syd.good.feature.netutils.NetUtilsActivity
import com.syd.good.feature.officialdocument.AsimpleActivity
import com.syd.good.feature.officialdocument.DrawableActivity
import com.syd.good.feature.officialdocument.MenuActivity
import com.syd.good.feature.recyclerview_study.RecyclerViewBaseActivity
import com.syd.good.feature.service.MyServiceConnection
import com.syd.good.feature.service.ServiceTestActivity
import com.syd.good.feature.service.TestService
import com.syd.good.feature.sqlite.SQLiteBaseActivity
import com.syd.good.feature.tabmenu.TabHostActivity
import com.syd.good.feature.test.TestMainActivity
import com.syd.good.feature.viewbinding.ViewBindingActivity
import com.syd.good.feature.webview.WebViewUseActivity
import com.syd.good.feature.xmlparse.JsonParseActivity
import com.syd.good.feature.xmlparse.XmlParseActivity
import com.syd.good.software.BeaconInfoActivity
import com.syd.good.software.login.LoginByPhoneActivity
import com.syd.good.utils.L

class MainActivity : BaseActivity() {
    override fun layoutId() = R.layout.activity_main


    val datas = listOf(
        MainContentData("Hello", 1, MainActivity::class.java),

        MainContentData("Dialog", 2, DialogMainStudyActivity::class.java),
        MainContentData("EventBus", 2, EventBusMainActivity::class.java),
        MainContentData("Fragment", 2, FragmentMainActivity::class.java),
        MainContentData("Fragment 静态添加", 2, FragmentStaticActivity::class.java),
        MainContentData("Test", 2, TestMainActivity::class.java),
        MainContentData("MDC", 1, TestMainActivity::class.java),
        MainContentData("Material Design", 2, MDCMainActivity::class.java),
        MainContentData("Button", 2, MDCButtonsActivity::class.java),
        MainContentData("Drawable 资源", 1, MainActivity::class.java),
        MainContentData("layer-list", 2, DrawableResourceActivity::class.java),
        MainContentData("动画", 1, MainActivity::class.java),
        MainContentData("属性动画", 2, AnimatorActivity::class.java),
        MainContentData("淡入淡出视图", 2, CrossfadeActivity::class.java),
        MainContentData("卡片翻转", 2, CardFlipActivity::class.java),
        MainContentData("补间动画", 2, ViewAnimationActivity::class.java),
        MainContentData("插值器", 2, InterpolatorBaseUseActivity::class.java),
        MainContentData("揭露动画", 2, RevealAnimationActivity::class.java),
        MainContentData("ViewBinding", 2, ViewBindingActivity::class.java),
        MainContentData("RecyclerView", 2, RecyclerViewBaseActivity::class.java),
        MainContentData("TabHost", 2, com.syd.good.feature.tabhost.TabHostActivity::class.java),
        MainContentData("TabHostFragment", 2, com.syd.good.feature.tabhost.TabHostFragmentActivity::class.java),
        MainContentData("TabHost测试", 2, com.syd.good.feature.tabhost.TabHostFActivity::class.java),


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
        MainContentData("事件处理", 2, EventDispatcherActivity::class.java),

        MainContentData("官方文档", 1, MainActivity::class.java),
        MainContentData("构建首个应用", 2, AsimpleActivity::class.java),
        MainContentData("可绘制对象", 2, DrawableActivity::class.java),
        MainContentData("菜单", 2, MenuActivity::class.java),
        MainContentData("ConstraintLayout", 2, ConstraintLayoutActivity::class.java),

        MainContentData("软著申请假页面", 1, MainActivity::class.java),
        MainContentData("Beacon信息", 2, BeaconInfoActivity::class.java),
        MainContentData("登录页面", 2, LoginByPhoneActivity::class.java)
    )

    override fun init(savedInstanceState: Bundle?) {
        Log.e(javaClass.simpleName, "$taskId==")
        val recyclerView: RecyclerView = findViewById(R.id.rlv)
        val adapter = MainAdapter()
        recyclerView.adapter = adapter
        val layoutManager = GridLayoutManager(this, 4)
        layoutManager.spanSizeLookup = MySanSizeLookup()
        recyclerView.layoutManager = layoutManager

        val intent = Intent(this, TestService::class.java)
        bindService(intent, MyServiceConnection(), Service.BIND_AUTO_CREATE)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        L.e(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        L.e(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        L.e(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        L.e(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        L.e(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        L.e(TAG, "onDestroy")
    }

    inner class MySanSizeLookup : SpanSizeLookup() {

        override fun getSpanSize(position: Int): Int {
            if (datas[position].type == 1) {
                return 4
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
