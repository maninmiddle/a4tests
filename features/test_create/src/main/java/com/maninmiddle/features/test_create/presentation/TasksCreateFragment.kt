package com.maninmiddle.features.test_create.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maninmiddle.features.test_create.R
import com.maninmiddle.features.test_create.databinding.DialogInputBinding
import com.maninmiddle.features.test_create.databinding.FragmentTasksCreateBinding
import com.maninmiddle.features.test_create.domain.model.TaskModel
import com.maninmiddle.features.test_create.domain.model.VariantModel
import com.maninmiddle.features.test_create.presentation.adapter.TaskCreateAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class TasksCreateFragment : Fragment() {
    private var _binding: FragmentTasksCreateBinding? = null
    private val viewModel by viewModel<TestCreateViewModel>()
    private val binding: FragmentTasksCreateBinding
        get() = _binding ?: throw RuntimeException("FragmentTasksCreateBinding == null")
    private var variantCounter = 1
    private var testId = 30
    private lateinit var taskAdapter: TaskCreateAdapter
    private val taskList = mutableListOf<TaskModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksCreateBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            testId = it.getInt("testId", -1)
        }
        binding.addQuestionButton.setOnClickListener {
            addNewTask()
        }
        binding.ivGenerate.setOnClickListener {
            val dialogBinding = DialogInputBinding.inflate(layoutInflater)
            MaterialAlertDialogBuilder(requireActivity())
                .setTitle("Введите тему")
                .setView(dialogBinding.root)
                .setPositiveButton("OK") { _, _ ->
                    val inputText = dialogBinding.etTopic.text?.toString() ?: ""
                    if (inputText.isNotEmpty()) {
                        generateTest(inputText)
                    }
                }
                .setNegativeButton("Отмена", null)
                .show()
        }
        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        taskAdapter = TaskCreateAdapter()
        binding.recyclerView.adapter = taskAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.GoNextButton.setOnClickListener {
            if (taskAdapter.currentList.isEmpty()) {
                Toast.makeText(context, "Добавьте хотя бы один вопрос", Toast.LENGTH_SHORT).show()
            } else {
                val invalidTasks = getInvalidTasks()
                if (invalidTasks.isEmpty()) {
                    taskList.forEach { task ->
                        val correctCount = task.variants.count { it.isCorrect }
                        if (correctCount >= 2) {
                            task.type = "multiple_choice"
                        }
                        if (task.type == "text_input") {
                            task.variants.clear()
                            Log.d("STEP 2", task.correctAnswer.toString())
                        }
                    }
                    viewModel.createTasks(taskList)
                    Toast.makeText(requireActivity(), "ID Теста: $testId", Toast.LENGTH_LONG).show()
                    requireActivity().supportFragmentManager.popBackStack()
                } else {
                    showInvalidTasksErrors(invalidTasks)
                }
            }
        }
    }

    private fun generateTest(text: String) {
        viewModel.generateTasks(text)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.generateState.collect { data ->
                    createTasks(data.tasks)
                }
            }
        }
    }

    private fun createTasks(tasks: List<TaskModel>?) {
        tasks?.forEach { task ->
            addNewTask(task)
        }
    }

    private fun getInvalidTasks(): List<Pair<Int, String>> {
        return taskAdapter.currentList.mapIndexedNotNull { index, task ->
            val errorMessage = buildString {
                if (task.text.isBlank()) {
                    append(getString(R.string.stringTaskTextType))
                }

                when (task.type) {
                    "text_input" -> {
                        if (task.correctAnswer.isNullOrEmpty()) {
                            if (isNotEmpty()) append("\n")
                            append(getString(R.string.stringTypeTextAnswer))
                        }
                    }

                    else -> {
                        if (task.variants.size < 2) {
                            if (isNotEmpty()) append("\n")
                            append(getString(R.string.stringAddMinTwoVariant))
                        }

                        task.variants.forEachIndexed { variantIndex, variant ->
                            if (variant.text.isBlank()) {
                                if (isNotEmpty()) append("\n")
                                append("Вариант ${variantIndex + 1}: введите текст")
                            }
                        }
                    }
                }
            }

            if (errorMessage.isNotEmpty()) index to "Вопрос ${index + 1}:\n$errorMessage" else null
        }
    }

    private fun showInvalidTasksErrors(errors: List<Pair<Int, String>>) {
        errors.forEach { (_, message) ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun addNewTask(task: TaskModel? = null) {
        val taskModel = TaskModel(
            id = taskList.size + 1,
            testId = testId,
            type = "single_choice",
            text = task?.text ?: "",
            variants = task?.variants ?: mutableListOf(
                VariantModel(
                    id = variantCounter++,
                    taskId = taskList.size + 1,
                    text = "",
                    isCorrect = false
                )
            ),
            correctAnswer = ""
        )

        taskList.add(taskModel)
        taskAdapter.submitList(taskList.toList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}