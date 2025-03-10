package com.maninmiddle.features.test_result.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maninmiddle.features.test_result.R
import com.maninmiddle.features.test_result.databinding.FragmentTestResultBinding
import com.maninmiddle.features.test_result.domain.model.StatsItem
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.round
import kotlin.math.roundToInt


class TestResultFragment : Fragment() {
    private var _binding: FragmentTestResultBinding? = null
    private val viewModel by viewModel<TestResultViewModel>()
    private val binding: FragmentTestResultBinding
        get() = _binding ?: throw RuntimeException("FragmentTestResultBinding == null")
    private var rightAnswers = 30
    private var questions = 30
    private var name = ""
    private var testId = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            rightAnswers = it.getInt("rightAnswers", -1)
            questions = it.getInt("questions", -1)
            name = it.getString("fullName", "")
            testId = it.getInt("testId", 0)
        }

        binding.btnGoBackToTestList.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.tvRightAnswersCount.text =
            getString(R.string.stringRightAnswersCount, rightAnswers, questions)

        val percentage = round((rightAnswers.toDouble() / questions) * 100).toInt()
        binding.tvPercentage.text =
            getString(R.string.stringPercentage, percentage)

        binding.testsResultMark.text =
            ((rightAnswers * 100) / (questions * 10)).toFloat().roundToInt().toString()

        viewModel.createStat(
            StatsItem(0, name, testId, rightAnswers, questions)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}