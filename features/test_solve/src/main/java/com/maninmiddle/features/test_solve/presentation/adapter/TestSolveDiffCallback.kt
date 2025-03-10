package com.maninmiddle.features.test_solve.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.maninmiddle.features.test_solve.domain.model.VariantModel

class TestSolveDiffCallback : DiffUtil.ItemCallback<VariantModel>() {
    override fun areItemsTheSame(oldItem: VariantModel, newItem: VariantModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VariantModel, newItem: VariantModel): Boolean {
        return oldItem == newItem
    }

}