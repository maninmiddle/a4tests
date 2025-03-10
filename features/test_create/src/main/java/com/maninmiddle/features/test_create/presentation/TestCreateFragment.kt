package com.maninmiddle.features.test_create.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.maninmiddle.core.common.util.MainActivityFragmentContract
import com.maninmiddle.features.test_create.R
import com.maninmiddle.features.test_create.databinding.FragmentTestCreateBinding
import com.maninmiddle.features.test_create.domain.model.TestItem
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestCreateFragment : Fragment() {
    private var _binding: FragmentTestCreateBinding? = null
    private val binding: FragmentTestCreateBinding
        get() = _binding ?: throw RuntimeException("FragmentTestCreateBinding == null")
    private val viewModel by viewModel<TestCreateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.inputTitle.error = null
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etCategoryTest.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.inputType.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnNextStep.setOnClickListener {
            if (validateInputs()) {
                viewModel.createTest(
                    TestItem(
                        0,
                        name = binding.etTitle.text.toString(),
                        subject = binding.etCategoryTest.text.toString(),
                        completeTime = 10,
                        password = "dsggs"
                    )
                )

                lifecycleScope.launch {
                    viewModel.state.collect { state ->
                        if (!state.isLoading) {
                            val fragment = TasksCreateFragment().apply {
                                arguments = Bundle().apply {
                                    putInt("testId", state.test!!.id)
                                }
                            }
                            val mainActivity = requireActivity() as MainActivityFragmentContract
                            mainActivity.replaceFragment(fragment, false)
                        }
                    }
                }
            }
        }
    }


    private fun validateInputs(): Boolean {
        val title = binding.etTitle.text.toString().trim()
        val category = binding.etCategoryTest.text.toString().trim()

        var isValid = true

        if (title.isEmpty()) {
            binding.inputTitle.error = getString(R.string.stringEnterTestName)
            isValid = false
        } else {
            binding.inputTitle.error = null
        }

        if (category.isEmpty()) {
            binding.inputType.error = getString(R.string.stringTestCategoryName)
            isValid = false
        } else {
            binding.inputType.error = null
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}