package com.maninmiddle.features.test_create.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.maninmiddle.features.test_create.domain.model.TaskModel

class TaskDiffCallback : DiffUtil.ItemCallback<TaskModel>() {
    override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
        return oldItem == newItem
    }
}