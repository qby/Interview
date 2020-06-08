package com.qibenyu.explore.tiktok

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.github.rubensousa.gravitysnaphelper.GravityPagerSnapHelper
import com.qibenyu.explore.R
import kotlinx.android.synthetic.main.activity_tiktok.*
import java.util.*


class TikTokActivity : Activity() {

    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiktok)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        linearLayoutManager = LinearLayoutManager(this)

        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = TikTokAdapter()

        GravityPagerSnapHelper(Gravity.BOTTOM, true) {

        }.attachToRecyclerView(recyclerview)


    }

    class TikTokAdapter() : RecyclerView.Adapter<TikTokViewHolder>() {

        private val random = Random()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TikTokViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_tik_tok, parent, false)
            return TikTokViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: TikTokViewHolder, position: Int) {

            val r = random.nextInt(256)
            val g = random.nextInt(256)
            val b = random.nextInt(256)

            holder.itemView.setBackgroundColor(Color.rgb(r, g, b))
        }

    }

    class TikTokViewHolder(view: View) : ViewHolder(view) {
    }

}

