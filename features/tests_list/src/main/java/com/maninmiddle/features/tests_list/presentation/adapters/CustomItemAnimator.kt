package com.maninmiddle.features.tests_list.presentation.adapters

import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class CustomItemAnimator: DefaultItemAnimator() {
    override fun animateAdd(holder: RecyclerView.ViewHolder): Boolean {
        val position = holder.adapterPosition
        val view = holder.itemView

        val initialTranslationX = if (position % 2 == 0) {
            -view.width.toFloat()
        } else {
            view.width.toFloat()
        }

        view.translationX = initialTranslationX
        view.alpha = 0f

        view.animate()
            .translationX(0f)
            .alpha(1f)
            .setDuration(400)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()

        return false
    }
}