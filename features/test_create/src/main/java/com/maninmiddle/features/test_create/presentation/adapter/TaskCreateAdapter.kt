package com.maninmiddle.features.test_create.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.maninmiddle.features.test_create.databinding.TasksCreateItemBinding
import com.maninmiddle.features.test_create.domain.model.TaskModel

class TaskCreateAdapter(
) : ListAdapter<TaskModel, TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view =
            TasksCreateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task, position)
    }
}