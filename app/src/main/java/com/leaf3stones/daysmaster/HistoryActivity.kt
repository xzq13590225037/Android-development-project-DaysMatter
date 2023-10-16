package com.leaf3stones.daysmaster

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.daysmatter_lhp.HistoryAdapter
import com.leaf3stones.daysmaster.bean.HistoryBean


class HistoryActivity : AppCompatActivity(), View.OnClickListener {
    private var emptyTv: TextView? = null
    private var historyLv: ListView? = null
    private var backIv: ImageView? = null
    var mDatas: MutableList<HistoryBean.ResultBean>? = null
    private var adapter: HistoryAdapter? = null
    var historyBean: HistoryBean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        emptyTv = findViewById(R.id.history_tvEmpty) as TextView
        historyLv = findViewById(R.id.historyLv) as ListView
        backIv = findViewById(R.id.history_imgBack) as ImageView
        backIv!!.setOnClickListener(this)
        mDatas = ArrayList()
        adapter = HistoryAdapter(this, mDatas as ArrayList<HistoryBean.ResultBean>)
        historyLv!!.adapter = adapter
        try {
            val intent = intent
            val bundle = intent.extras
            historyBean = bundle!!.getSerializable("history") as HistoryBean?
            val list: List<HistoryBean.ResultBean>? = historyBean?.result
            list?.let { (mDatas as ArrayList<HistoryBean.ResultBean>).addAll(it) }
            adapter!!.notifyDataSetChanged()
        } catch (e: Exception) {
            emptyTv!!.visibility = View.VISIBLE
        }
//        historyLv!!.onItemClickListener =
//            OnItemClickListener { parent, view, position, id ->
//                val intent = Intent(this@HistoryActivity, HistoryDescActivity::class.java)
//                val resultBean = mDatas.get(position)
//                val bean_id = resultBean.get_id()
//                intent.putExtra("hisId", bean_id)
//                startActivity(intent)
//            }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.history_imgBack -> finish()
        }
    }
}




