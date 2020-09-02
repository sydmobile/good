package com.syd.good.feature.main

import android.content.Intent
import android.os.Bundle
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
import com.syd.good.data.MainContentData
import com.syd.good.feature.dialog.DialogMainStudyActivity

class MainActivity : BaseActivity() {
    override fun layoutId() = R.layout.activity_main
    val datas = listOf(
        MainContentData("Hello", 1, MainActivity::class.java),
        MainContentData("Dialog", 2, DialogMainStudyActivity::class.java),
        MainContentData("Hello", 2, MainActivity::class.java),
        MainContentData("Helloffff", 2, MainActivity::class.java),
        MainContentData("Helloffff", 2, MainActivity::class.java),
        MainContentData("Hello", 2, MainActivity::class.java),
        MainContentData("Helldddo", 2, MainActivity::class.java),
        MainContentData("Heldddlo", 2, MainActivity::class.java),
        MainContentData("dd", 2, MainActivity::class.java),
        MainContentData("Helwwwwwwwlo", 2, MainActivity::class.java),
        MainContentData("Hewllo", 2, MainActivity::class.java),
        MainContentData("Hello", 1, MainActivity::class.java),
        MainContentData("Hello", 2, MainActivity::class.java),
        MainContentData("Hello", 2, MainActivity::class.java),
        MainContentData("Helloffff", 2, MainActivity::class.java),
        MainContentData("Helloffff", 2, MainActivity::class.java),
        MainContentData("Hello", 2, MainActivity::class.java),
        MainContentData("Helldddo", 2, MainActivity::class.java),
        MainContentData("Heldddlo", 2, MainActivity::class.java),
        MainContentData("dd", 2, MainActivity::class.java),
        MainContentData("Helwwwwwwwlo", 2, MainActivity::class.java),
        MainContentData("Hewllo", 2, MainActivity::class.java)
    )

    override fun init(savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = findViewById(R.id.rlv)
        val adapter: MainAdapter = MainAdapter()
        recyclerView.adapter = adapter
        val layoutManager = GridLayoutManager(this, 4)
        layoutManager.spanSizeLookup = MySanSizeLookup()
        recyclerView.layoutManager = layoutManager

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


    inner class MainAdapter(val layoutInflater: LayoutInflater = LayoutInflater.from(this@MainActivity)) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemViewType(position: Int) = datas[position].type

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            if (viewType == 1) {
                val view = layoutInflater.inflate(R.layout.main_item_title, parent, false)
                val viewHolder = TitleViewHolder(view)

                return TitleViewHolder(view)
            } else {
                val view = layoutInflater.inflate(R.layout.main_item_card, parent, false)
                val viewHolder = ContentViewHolder(view)
                viewHolder.cardView?.setOnClickListener {
                    val intent = Intent(this@MainActivity, datas[viewHolder.adapterPosition].cls)
                    startActivity(intent)
                }
                return viewHolder
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
