package com.leaf3stones.daysmaster

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import com.example.daysmatter_lhp.HistoryAdapter
import com.leaf3stones.daysmaster.base.BaseActivity
import com.leaf3stones.daysmaster.base.ContentURL.getTodayHistoryURL
import com.leaf3stones.daysmaster.bean.HistoryBean
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class MainActivity_lhp : BaseActivity(), View.OnClickListener {
    private var mainLv: ListView? = null
    private var imgBtn: ImageButton? = null
    var mDatas: MutableList<HistoryBean.ResultBean>? = null
    private var adapter: HistoryAdapter? = null
    private var calendar: Calendar? = null
    private var date: Date? = null
    var historyBean: HistoryBean? = null


    var dayTv: TextView? = null
    var weekTv: TextView? = null
    var yangliTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_lhp)

        mainLv = findViewById<View>(R.id.main_lv) as ListView
        imgBtn = findViewById<View>(R.id.main_imgbtn) as ImageButton
        imgBtn!!.setOnClickListener(this)
        mDatas = ArrayList()
        adapter = HistoryAdapter(this, mDatas as ArrayList<HistoryBean.ResultBean>)
        mainLv!!.adapter = adapter
        //        获取日历对象
        calendar = Calendar.getInstance()
        date = Date()
        calendar!!.setTime(date)
        val month = calendar!!.get(Calendar.MONTH) + 1
        val day = calendar!!.get(Calendar.DAY_OF_MONTH)
        addHeaderAndFooterView()
        val todayHistoryURL = getTodayHistoryURL("1.0", month, day)
        loadData(todayHistoryURL)

    }

    private fun addHeaderAndFooterView() {
//        给ListView添加头尾布局函数
        val headerView: View = LayoutInflater.from(this).inflate(R.layout.main_header, null)
        initHeaderView(headerView)
        mainLv!!.addHeaderView(headerView)
        val footerView = LayoutInflater.from(this).inflate(R.layout.main_footer, null)
        footerView.tag = "footer"
        footerView.setOnClickListener(this)
        mainLv!!.addFooterView(footerView)
    }

    private fun initHeaderView(headerView: View) {
        /* 初始化ListView头布局当中的每一个控件*/
//        yinliTv = headerView.findViewById(R.id.main_header_tv_nongli)
        dayTv = headerView.findViewById(R.id.main_header_tv_day)
        weekTv = headerView.findViewById(R.id.main_header_tv_week)
        yangliTv = headerView.findViewById(R.id.main_header_tv_yangli)

        //        将日期对象转换成指定格式的字符串形式
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val time = sdf.format(date)
//        val laohuangliURL = getLaohuangliURL(time)
//        loadHeaderData(laohuangliURL)
    }

    private fun getWeek(year: Int, month: Int, day: Int): String {
//        根据年月日获取对应的星期
        val calendar = Calendar.getInstance()
        calendar[year, month - 1] = day
        val weeks = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
        var index = calendar[Calendar.DAY_OF_WEEK] - 1
        if (index < 0) {
            index = 0
        }
        return weeks[index]
    }

    override fun onSuccess(result: String?) {
        mDatas!!.clear()
        historyBean = Gson().fromJson(result, HistoryBean::class.java)
        val list = historyBean?.result
        for (i in 0..4) {
            mDatas!!.add(list!![i])
        }
        adapter?.notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        if (v.id === R.id.main_imgbtn) {
            popCalendarDialog()
            return
        }
        val tag = v.tag as String
        if (tag == "footer") {
            val intent = Intent(this, HistoryActivity::class.java)
            if (historyBean != null) {
                val bundle = Bundle()
                bundle.putSerializable("history", historyBean)
                intent.putExtras(bundle)
            }
            startActivity(intent)
        }
    }

    private fun popCalendarDialog() {
//        弹出日历对话框
        val calendar = Calendar.getInstance()
        val dialog = DatePickerDialog(
            this,
            { view, year, month, dayOfMonth -> // //                改变老黄历显示的内容
                //                 String time = year+"-"+(month+1)+"-"+dayOfMonth;
                //                 String laohuangliURL = ContentURL.getLaohuangliURL(time);
                //                 loadHeaderData(laohuangliURL);

                //                改变历史上的今天数据
                val todayHistoryURL = getTodayHistoryURL("1.0", month + 1, dayOfMonth)
                loadData(todayHistoryURL)
            }, calendar[Calendar.YEAR], calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )
        dialog.show()
    }
}