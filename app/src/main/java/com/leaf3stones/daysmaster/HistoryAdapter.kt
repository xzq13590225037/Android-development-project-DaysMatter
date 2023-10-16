package com.example.daysmatter_lhp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.leaf3stones.daysmaster.bean.HistoryBean
import com.leaf3stones.daysmaster.R
import com.leaf3stones.daysmaster.R.layout.item_main_timeline


class HistoryAdapter(var context: Context, var mDatas: List<HistoryBean.ResultBean>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return mDatas.size
    }

    override fun getItem(position: Int): Any {
        return mDatas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        var holder: ViewHolder? = null
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(item_main_timeline, null)
            holder = ViewHolder(convertView)
            convertView!!.setTag(holder)
        } else {
            holder = convertView.tag as ViewHolder
        }
        val resultBean = mDatas[position]
        //        判断当前位置的年份和上一个位置是否相同
        if (position != 0) {
            val lastBean = mDatas[position - 1]
            if (resultBean.year == lastBean.year) {
                holder.timeLayout.visibility = View.GONE
            } else {
                holder!!.timeLayout.visibility = View.VISIBLE
            }
        } else {
            holder!!.timeLayout.visibility = View.VISIBLE
        }
        holder!!.timeTv.text =
            resultBean.year.toString() + "-" + resultBean.month + "-" + resultBean.day
        holder.titleTv.text = resultBean.title
//        val picURL = resultBean.pic
////        if (TextUtils.isEmpty(picURL)) {
////            holder.picIv.visibility = View.GONE
////        } else {
////            holder.picIv.visibility = View.VISIBLE
////            Picasso.with(context).load(picURL).into(holder.picIv)
////        }
        return convertView
    }

    internal inner class ViewHolder(itemView: View) {
        var timeTv: TextView
        var titleTv: TextView
        var timeLayout: LinearLayout

        init {
            timeTv = itemView.findViewById(R.id.item_main_time)
            titleTv = itemView.findViewById(R.id.item_main_title)
            timeLayout = itemView.findViewById(R.id.item_main_ll)
        }
    }
}


