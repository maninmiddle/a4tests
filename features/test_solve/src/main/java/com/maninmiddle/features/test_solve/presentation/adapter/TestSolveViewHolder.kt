package com.maninmiddle.features.test_solve.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.maninmiddle.features.test_solve.databinding.TestSolveButtonBinding
import com.maninmiddle.features.test_solve.domain.model.VariantModel

class TestSolveViewHolder(
    val binding: TestSolveButtonBinding,
    private val onAnswerSelected: (Boolean) -> Unit,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(variantModel: VariantModel, taskType: String) {
        binding.testSolveButton.text = variantModel.text
        if (taskType == "multiple_choice") {
            binding.checkbox.visibility = View.VISIBLE
        } else {
            binding.checkbox.visibility = View.GONE
            binding.testSolveCard.setOnClickListener {
                onAnswerSelected(variantModel.isCorrect)
            }
        }
    }
}