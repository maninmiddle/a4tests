package com.maninmiddle.features.tests_list.presentation

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maninmiddle.core.common.util.MainActivityFragmentContract
import com.maninmiddle.features.test_solve.presentation.TestSolveFragment
import com.maninmiddle.features.tests_list.R
import com.maninmiddle.features.tests_list.databinding.FragmentTestsListBinding
import com.maninmiddle.features.tests_list.presentation.adapters.CustomItemAnimator
import com.maninmiddle.features.tests_list.presentation.adapters.TestsListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class TestsListFragment : Fragment() {
    private var _binding: FragmentTestsListBinding? = null
    private val viewModel by viewModel<TestsListViewModel>()
    private val binding: FragmentTestsListBinding
        get() = _binding ?: throw RuntimeException("FragmentTestsListFragment == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestsListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.ivSearch.setOnClickListener {
            showSearchByIdDialog()
        }
        setupRv()
    }

    private fun startSolveTestFragment(testId: Int) {
        val mainActivity = requireActivity() as MainActivityFragmentContract
        val fragment = TestSolveFragment().apply {
            arguments = Bundle().apply {
                putInt("testId", testId)
            }
        }
        mainActivity.replaceFragment(fragment)
    }

    private fun showSearchByIdDialog() {
        val input = EditText(requireContext()).apply {
            inputType = InputType.TYPE_CLASS_NUMBER
            setPadding(40, 20, 40, 20)
        }

        MaterialAlertDialogBuilder(requireContext(), R.style.RoundedDialog)
            .setTitle("Введите ID Теста")
            .setView(input)
            .setPositiveButton("Войти") { _, _ ->
                val testId = input.text.toString()
                startSolveTestFragment(testId.toInt())
            }
            .setNegativeButton("Назад", null)
            .show()
    }

    private fun showJoinTestDialog(onConfirm: () -> Unit) {
        MaterialAlertDialogBuilder(requireContext(), R.style.RoundedDialog)
            .setTitle("Подтверждение")
            .setMessage("Вы действительно хотите присоединиться к тесту?")
            .setPositiveButton("Да") { _, _ -> onConfirm() }
            .setNegativeButton("Нет", null)
            .show()
    }

    private fun setupRv() {
        val testsListAdapter = TestsListAdapter { test ->
            showJoinTestDialog {
                startSolveTestFragment(test.id)
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    if (!state.isLoading) {
                        binding.progressCircular.visibility = View.GONE
                        testsListAdapter.submitList(state.tests)
                    } else {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                }
            }
        }
        binding.rvLayout.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLayout.itemAnimator = CustomItemAnimator()
        binding.rvLayout.adapter = testsListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

