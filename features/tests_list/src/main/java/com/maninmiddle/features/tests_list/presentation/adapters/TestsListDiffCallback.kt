package com.maninmiddle.features.tests_list.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.maninmiddle.features.tests_list.domain.model.TestItem

class TestsListDiffCallback: DiffUtil.ItemCallback<TestItem>() {
    override fun areItemsTheSame(oldItem: TestItem, newItem: TestItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TestItem, newItem: TestItem): Boolean {
        return oldItem == newItem
    }
}