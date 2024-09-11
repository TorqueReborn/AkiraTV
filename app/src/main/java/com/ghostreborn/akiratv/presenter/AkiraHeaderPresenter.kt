package com.ghostreborn.akiratv.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.RowHeaderPresenter
import com.ghostreborn.akiratv.R

class AkiraHeaderPresenter(val s: String) : RowHeaderPresenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.header_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder?, item: Any?) {

        viewHolder?.view?.isFocusable = true
        viewHolder?.view?.isFocusableInTouchMode = true
        val title = viewHolder?.view?.findViewById<TextView>(R.id.textView)
        title?.text = s

    }

    override fun onSelectLevelChanged(holder: ViewHolder?) {
        super.onSelectLevelChanged(holder)
        holder?.view?.run {
            val scale = 1f + holder.selectLevel * 0.1f
            scaleX = scale
            scaleY = scale
        }
    }
}