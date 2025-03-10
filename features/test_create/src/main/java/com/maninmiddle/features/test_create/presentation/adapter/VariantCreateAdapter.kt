package com.maninmiddle.features.test_create.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.maninmiddle.features.test_create.databinding.VariantCreateItemBinding
import com.maninmiddle.features.test_create.domain.model.VariantModel

class VariantCreateAdapter(

) : ListAdapter<VariantModel, VariantViewHolder>(VariantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariantViewHolder {
        val view =
            VariantCreateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VariantViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: VariantViewHolder, position: Int) {
        val variant = getItem(position)

        holder.bind(variant, position)
    }

}