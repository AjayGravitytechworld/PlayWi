package com.abc.demo.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.abc.demo.R
import com.abc.demo.listener.aItemView

class aaPanelAItemView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(
    context!!, attrs, defStyleAttr
), aItemView {
    private val overlay: View?

    init {
        inflate(context, R.layout.view_panel_item, this)
        overlay = findViewById(R.id.overlay)
    }

    override fun setFocus(isFocused: Boolean) {
        if (overlay != null) {
            overlay.visibility = if (isFocused) INVISIBLE else VISIBLE
        }
    }
}