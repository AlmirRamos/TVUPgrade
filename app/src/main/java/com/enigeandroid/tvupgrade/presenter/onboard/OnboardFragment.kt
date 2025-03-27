package com.enigeandroid.tvupgrade.presenter.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.enigeandroid.tvupgrade.R
import com.enigeandroid.tvupgrade.databinding.FragmentOnboardBinding

class OnboardFragment : Fragment() {

    private var _binding: FragmentOnboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.btnIniciarOnboard.setOnClickListener {
            findNavController().navigate(R.id.action_onboardFragment_to_navigation)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}