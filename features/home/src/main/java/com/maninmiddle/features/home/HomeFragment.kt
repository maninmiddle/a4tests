package com.maninmiddle.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maninmiddle.core.common.util.MainActivityFragmentContract
import com.maninmiddle.features.home.databinding.FragmentHomeBinding
import com.maninmiddle.features.test_create.presentation.TestCreateFragment
import com.maninmiddle.features.tests_list.presentation.TestsListFragment


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = requireActivity() as MainActivityFragmentContract
        binding.btnJoinTest.setOnClickListener {
            mainActivity.replaceFragment(TestsListFragment())
        }
        binding.btnCreateTest.setOnClickListener {
            mainActivity.replaceFragment(TestCreateFragment())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}