package com.leaf3stones.daysmaster.base

object ContentURL {
    //   获取指定日期对应的历史上的今天的网址
    fun getTodayHistoryURL(version: String, month: Int, day: Int): String {
//        String todayHistoryURL = "http://api.juheapi.com/japi/toh?key=6a877b186ccd134296d131183b74c9f4&v="+version+"&month="+month+"&day="+day;
        return "http://api.juheapi.com/japi/toh?key=300693d5603c27a3bed9959988ad97a5&v=$version&month=$month&day=$day"
    }

    //    获取指定日期老黄历的网址
    fun getLaohuangliURL(time: String): String {
        return "http://v.juhe.cn/laohuangli/d?date=$time&key=69ec1ef7bc0137599873d70ff516f8cf"
    }

    //    根据事件id获取到指定事件详情信息
    fun getHistoryDescURL(version: String, id: String): String {
//        String url = "http://api.juheapi.com/japi/tohdet?key=6a877b186ccd134296d131183b74c9f4&v="+version+"&id="+id;
        return "http://api.juheapi.com/japi/tohdet?key=7106ffc15413a3adcfe0842f23fdedc1&v=$version&id=$id"
    }
}