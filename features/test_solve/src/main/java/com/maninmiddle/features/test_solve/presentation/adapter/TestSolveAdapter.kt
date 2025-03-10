package com.maninmiddle.features.test_solve.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.maninmiddle.features.test_solve.databinding.TestSolveButtonBinding
import com.maninmiddle.features.test_solve.domain.model.VariantModel

class TestSolveAdapter(
    private val taskType: String,
    private val onAnswerSelected: (Boolean) -> Unit
) : ListAdapter<VariantModel, TestSolveViewHolder>(
    TestSolveDiffCallback()
) {
    private val selectedVariants = mutableSetOf<Int>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestSolveViewHolder {
        val view =
            TestSolveButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestSolveViewHolder(view, onAnswerSelected)
    }

    override fun onBindViewHolder(holder: TestSolveViewHolder, position: Int) {
        val variant = getItem(position)
        Log.d("CURRENT VARIANT", variant.toString())
        holder.bind(variant, taskType)
        holder.binding.testSolveCard.setOnClickListener {
            if (taskType == "single_choice") {
                onAnswerSelected(variant.isCorrect)
            } else {
                val isChecked = !holder.binding.checkbox.isChecked
                holder.binding.checkbox.isChecked = isChecked
                if (isChecked) {
                    selectedVariants.add(variant.id)
                } else {
                    selectedVariants.remove(variant.id)
                }
            }
        }
        holder.binding.checkbox.setOnClickListener {
            val isChecked = !holder.binding.checkbox.isChecked
            holder.binding.checkbox.isChecked = isChecked
            if (isChecked) {
                selectedVariants.add(variant.id)
            } else {
                selectedVariants.remove(variant.id)
            }
        }
    }

    fun checkAnswers(): Boolean {
        val currentList = currentList
        val correctIds =
            currentList.filter { it.isCorrect }.map { it.id }.toSet()
        return selectedVariants == correctIds
    }

}