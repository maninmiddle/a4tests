package com.maninmiddle.features.test_solve.presentation

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maninmiddle.core.common.util.MainActivityFragmentContract
import com.maninmiddle.features.test_result.presentation.TestResultFragment
import com.maninmiddle.features.test_solve.R
import com.maninmiddle.features.test_solve.databinding.FragmentTestSolveBinding
import com.maninmiddle.features.test_solve.domain.model.TaskModel
import com.maninmiddle.features.test_solve.presentation.adapter.TestSolveAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale


class TestSolveFragment : Fragment() {
    private var _binding: FragmentTestSolveBinding? = null
    private val viewModel by viewModel<TestSolveViewModel>()
    private var currentTaskIndex = 0
    private var rightAnswers = 0
    private lateinit var tasks: List<TaskModel>
    private val binding: FragmentTestSolveBinding
        get() = _binding ?: throw RuntimeException("FragmentTestSolveBinding == null")
    private var testId = 30
    private var completeTime = 0
    private var name = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestSolveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            testId = it.getInt("testId", -1)
            completeTime = it.getInt("completeTime", 0)
        }

        val input = EditText(requireContext()).apply {
            hint = "Фамилия и Имя"
            setSingleLine()
        }
        val dialog = MaterialAlertDialogBuilder(requireContext(), R.style.RoundedDialog)
            .setTitle("Дополнительная информация")
            .setView(input)
            .setPositiveButton("Начать") { _, _ ->
                val fullName = input.text.toString().trim()
                if (fullName.isNotEmpty()) {
                    name = fullName
                    if (completeTime != 0) {
                        binding.countDownTimer.visibility = View.VISIBLE
                        viewModel.startTimer(completeTime.toLong() * 60000)
                        observeTime()
                        observeTimeState()
                    }
                } else {
                    Toast.makeText(context, "Вы не ввели имя", Toast.LENGTH_SHORT).show()
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
            .setNegativeButton("Нет") { _, _ ->
                requireActivity().supportFragmentManager.popBackStack()
            }
            .show()


        viewModel.getTasks(testId)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    if (!state.isLoading) {
                        binding.progressCircular.visibility = View.GONE
                        if (state.tasks.isNullOrEmpty()) {
                            Toast.makeText(
                                requireActivity(),
                                "Тест сейчас недоступен",
                                Toast.LENGTH_LONG
                            ).show()
                            dialog.dismiss()
                            requireActivity().supportFragmentManager.popBackStack()
                        } else {
                            tasks = state.tasks
                            currentTaskIndex = 0
                            showNextTask()
                        }

                    } else {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun observeTimeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.canContinue.collect { canContinue ->
                    if (!canContinue) {
                        val mainActivity = requireActivity() as MainActivityFragmentContract
                        val resultFragment = TestResultFragment().apply {
                            arguments = Bundle().apply {
                                putInt("rightAnswers", rightAnswers)
                                putInt("questions", tasks.size)
                                putString("fullName", name)
                                putInt("testId", testId)
                            }
                        }
                        viewModel.cancelTimer()
                        mainActivity.replaceFragment(resultFragment, false)
                    }
                }
            }
        }
    }

    private fun observeTime() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.timerValue.collect { mills ->
                    val totalSeconds = mills / 1000
                    val minutes = (totalSeconds / 60)
                    val seconds = totalSeconds % 60
                    binding.countDownTimer.text =
                        String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
                }
            }
        }
    }

    fun answer(isRight: Boolean = false) {
        if (isRight) rightAnswers++

        currentTaskIndex++

        if (currentTaskIndex >= tasks.size) {
            val mainActivity = requireActivity() as MainActivityFragmentContract
            val resultFragment = TestResultFragment().apply {
                arguments = Bundle().apply {
                    putInt("rightAnswers", rightAnswers)
                    putInt("questions", tasks.size)
                    putString("fullName", name)
                    putInt("testId", testId)
                }
            }
            mainActivity.replaceFragment(resultFragment, false)
        } else {
            showNextTask()
        }
    }

    private fun showNextTask() {

        val task = tasks[currentTaskIndex]
        binding.taskText.text = task.text

        when (task.type) {
            "single_choice", "multiple_choice" -> {
                setupRecyclerView(task)
                binding.fabSubmit.visibility =
                    if (task.type == "multiple_choice") View.VISIBLE else View.GONE
            }

            "text_input" -> {
                showTextInputTask(task)
            }
        }

    }

    private fun showTextInputTask(task: TaskModel) {
        binding.rvLayout.visibility = View.GONE
        binding.inputAnswer.visibility = View.VISIBLE
        binding.fabSubmit.visibility = View.VISIBLE
        binding.fabSubmit.setOnClickListener {
            val userAnswer = binding.etAnswer.text?.toString()?.trim()
            val correctAnswer = task.correctAnswer?.trim()
            val isCorrect = userAnswer?.equals(correctAnswer, ignoreCase = true) == true
            answer(isCorrect)
        }

    }

    private fun setupRecyclerView(task: TaskModel) {
        binding.rvLayout.visibility = View.VISIBLE
        binding.inputAnswer.visibility = View.GONE
        val adapter = TestSolveAdapter(task.type) { isCorrect ->
            answer(isCorrect)
        }
        adapter.submitList(task.variants)
        binding.rvLayout.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLayout.adapter = adapter
        binding.fabSubmit.setOnClickListener {
            val isCorrect = adapter.checkAnswers()
            answer(isCorrect)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}