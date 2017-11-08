package me.aluceps.sandbox.view.main

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.Spanned
import android.view.View
import me.aluceps.sandbox.BR
import me.aluceps.sandbox.model.ConnpassEvent

class MainViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding by lazy {
        DataBindingUtil.bind<ViewDataBinding>(itemView)
    }

    var title: String? = null

    var catchcopy: String? = null

    var showCatchCopy: Boolean = false

    lateinit var description: Spanned

    operator fun set(type: MainAdapter.Type, model: ConnpassEvent.Event) {
        if (type === MainAdapter.Type.DATA) {
            title = model.title
            catchcopy = model.catchcopy
            showCatchCopy = !model.catchcopy!!.isEmpty()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                description = Html.fromHtml(model.description, Html.FROM_HTML_MODE_LEGACY)
            }
        }
        binding.setVariable(BR.event, this)
    }
}
