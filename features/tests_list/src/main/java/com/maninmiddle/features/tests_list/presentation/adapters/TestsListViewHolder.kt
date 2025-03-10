package com.maninmiddle.features.tests_list.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.maninmiddle.features.tests_list.databinding.TestsListItemBinding
import com.maninmiddle.features.tests_list.domain.model.TestItem
import java.util.Locale

class TestsListViewHolder(val binding: TestsListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(test: TestItem, onTestClick: (TestItem) -> Unit) {
        binding.testName.text = test.name
        binding.testSubject.text = test.subject
        binding.testCompleteTime.text =
            String.format(Locale.getDefault(), "%d", test.completeTime)

        binding.root.setOnClickListener {
            onTestClick(test)
        }
    }
}