package com.syd.good.feature.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.syd.good.R
import com.syd.good.base.BaseActivity
import com.syd.good.data.MainContentData
import com.syd.good.feature.dialog.DialogMainStudyActivity

/**
 * <p>
 * date: 2020/9/3 17:56
 * @author syd
 * @version 1.0
 */
class TestMainActivity : BaseActivity() {
    val datas = listOf(
        MainContentData("测试内容", 1, DialogMainStudyActivity::class.java),
        MainContentData("Hello", 2, DialogMainStudyActivity::class.java),
        MainContentData("World", 3, DialogMainStudyActivity::class.java),
        MainContentData("！", 4, DialogMainStudyActivity::class.java)
    )

    override fun layoutId() = R.layout.common_activity

    override fun init(savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = findViewById(R.id.rlv)
        val adapter = MainAdapter()
        recyclerView.adapter = adapter
        val layoutManager = GridLayoutManager(this, 4)
        layoutManager.spanSizeLookup = MySanSizeLookup()
        recyclerView.layoutManager = layoutManager

    }



    /** 跨列使用 */
    inner class MySanSizeLookup : GridLayoutManager.SpanSizeLookup() {

        override fun getSpanSize(position: Int): Int {
            if (datas[position].type == 1) {
                return 4
            } else {
                return 1
            }
        }

    }

    inner class MainAdapter(private val layoutInflater: LayoutInflater = LayoutInflater.from(this@TestMainActivity)) :
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
                    val data = datas[viewHolder.adapterPosition]
                    when (data.type) {
                        // TODO 测试内容
                        2 -> {
                            Toast.makeText(this@TestMainActivity, data.title, Toast.LENGTH_SHORT)
                                .show()
                        }
                        3 -> {
                            Toast.makeText(this@TestMainActivity, data.title, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
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