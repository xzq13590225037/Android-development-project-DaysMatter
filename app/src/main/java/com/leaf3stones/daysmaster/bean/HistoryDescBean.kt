package com.leaf3stones.daysmaster.bean


class HistoryDescBean {

    var reason: String? = null
    var error_code = 0
    var result: List<ResultBean>? = null

    class ResultBean {

        private var _id: String? = null
        var title: String? = null
        var pic: String? = null
        var year = 0
        var month = 0
        var day = 0
        var des: String? = null
        var content: String? = null
        var lunar: String? = null

        fun get_id(): String? {
            return _id
        }

        fun set_id(_id: String?) {
            this._id = _id
        }
    }
}