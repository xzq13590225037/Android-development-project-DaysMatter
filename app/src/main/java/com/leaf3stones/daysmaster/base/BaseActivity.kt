package com.leaf3stones.daysmaster.base
import androidx.appcompat.app.AppCompatActivity;


import org.xutils.common.Callback.CancelledException
import org.xutils.common.Callback.CommonCallback
import org.xutils.http.RequestParams
import org.xutils.x


open class BaseActivity : AppCompatActivity(), CommonCallback<String?> {
    fun loadData(url: String?) {
        val params = RequestParams(url)
        x.http().get(params, this)
    }

    override fun onSuccess(result: String?) {}
    override fun onError(ex: Throwable, isOnCallback: Boolean) {}
    override fun onCancelled(cex: CancelledException) {}
    override fun onFinished() {}
}