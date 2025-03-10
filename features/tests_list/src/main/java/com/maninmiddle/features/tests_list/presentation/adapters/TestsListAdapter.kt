package com.maninmiddle.features.tests_list.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.maninmiddle.features.tests_list.databinding.TestsListItemBinding
import com.maninmiddle.features.tests_list.domain.model.TestItem

class TestsListAdapter(
    private val onTestClick: (TestItem) -> Unit
) : ListAdapter<TestItem, TestsListViewHolder>(
    TestsListDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestsListViewHolder {
        val view = TestsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TestsListViewHolder, position: Int) {
        val test = getItem(position)

        holder.bind(test, onTestClick)
    }
}