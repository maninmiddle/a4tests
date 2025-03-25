package com.maninmiddle.features.test_create.presentation.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maninmiddle.features.test_create.R
import com.maninmiddle.features.test_create.databinding.TasksCreateItemBinding
import com.maninmiddle.features.test_create.domain.model.TaskModel
import com.maninmiddle.features.test_create.domain.model.VariantModel
import java.util.Locale

class TaskViewHolder(
    private val binding: TasksCreateItemBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {
    private var variantId = 1
    fun bind(task: TaskModel, currentPosition: Int) {
        val variantAdapter = VariantCreateAdapter()
        binding.taskCreateItemRecycler.adapter = variantAdapter
        binding.taskCreateItemRecycler.layoutManager = LinearLayoutManager(context)
        binding.etTask.setText(task.text)
        if (task.variants.size > 0) {
            variantAdapter.submitList(task.variants.toList())
        }

        binding.taskCreateItemText.hint = String.format(
            Locale.getDefault(),
            "%s %d",
            context.getString(R.string.stringTask),
            currentPosition + 1
        )

        binding.etTask.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                task.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {}

        })


        binding.taskCreateItemAddVariant.setOnClickListener {
            task.variants.add(
                VariantModel(
                    id = variantId++,
                    taskId = task.id,
                    text = "",
                    isCorrect = false
                )
            )
            variantAdapter.submitList(task.variants.toList())
        }
        binding.addTextAnswerButton.setOnClickListener {
            binding.taskCreateItemAddVariant.visibility = View.GONE
            binding.addTextAnswerButton.visibility = View.GONE
            binding.textVariantCreateTextInput.visibility = View.VISIBLE
            binding.ivClose.visibility = View.VISIBLE
            task.type = "text_input"
            binding.taskCreateItemRecycler.visibility = View.GONE
            binding.etTextVariantInput.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    task.correctAnswer = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
        binding.ivClose.setOnClickListener {
            binding.taskCreateItemAddVariant.visibility = View.VISIBLE
            binding.addTextAnswerButton.visibility = View.VISIBLE
            binding.textVariantCreateTextInput.visibility = View.GONE
            binding.ivClose.visibility = View.GONE
            binding.taskCreateItemRecycler.visibility = View.VISIBLE
            task.type = "single_choice"
        }
    }
}