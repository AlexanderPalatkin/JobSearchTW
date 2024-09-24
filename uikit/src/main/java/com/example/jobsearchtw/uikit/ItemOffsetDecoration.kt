package com.example.jobsearchtw.uikit

import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(
    leftOffsetDp: Int,
    topOffsetDp: Int,
    rightOffsetDp: Int,
    bottomOffsetDp: Int
) : RecyclerView.ItemDecoration() {

    private val leftOffsetPx = dpToPx(leftOffsetDp)
    private val topOffsetPx = dpToPx(topOffsetDp)
    private val rightOffsetPx = dpToPx(rightOffsetDp)
    private val bottomOffsetPx = dpToPx(bottomOffsetDp)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(leftOffsetPx, topOffsetPx, rightOffsetPx, bottomOffsetPx)
    }

    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }
}