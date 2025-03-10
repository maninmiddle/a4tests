package com.maninmiddle.features.test_create.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.maninmiddle.features.test_create.domain.model.VariantModel

class VariantDiffCallback : DiffUtil.ItemCallback<VariantModel>() {
    override fun areItemsTheSame(oldItem: VariantModel, newItem: VariantModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VariantModel, newItem: VariantModel): Boolean {
        return oldItem == newItem
    }

}
