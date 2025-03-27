package com.enigeandroid.tvupgrade.presenter.auth.forgot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.hellodev.netflix.util.FirebaseHelper
import com.bumptech.glide.Glide
import com.enigeandroid.tvupgrade.R
import com.enigeandroid.tvupgrade.databinding.FragmentForgotBinding
import com.enigeandroid.tvupgrade.util.StateView
import com.enigeandroid.tvupgrade.util.hideKeyboard
import com.enigeandroid.tvupgrade.util.initToolbar
import com.enigeandroid.tvupgrade.util.isEmailValid
import com.enigeandroid.tvupgrade.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotFragment : Fragment() {

    private val viewModel: ForgotViewModel by viewModels()

    private var _binding: FragmentForgotBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar( toolbar = binding.toolbarForgot)
        initListener()
    }

    private fun initListener() {
        binding.btnRecoveryForgot.setOnClickListener {
            validateData()
        }
        Glide
            .with(requireContext())
            .load(R.drawable.loading)
            .into(binding.pbForgot)
    }

    private fun validateData() {
        val email = binding.editEmailForgot.text.toString()
        if (email.isEmailValid()) {
            hideKeyboard()
            forgot(email)
        } else {
            showSnackBar(R.string.text_email_empty_forgot_fragment)
        }
    }

    private fun forgot(email: String) {
        viewModel.forgot(email).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.pbForgot.isVisible = true
                }
                is StateView.Success -> {
                    showSnackBar(R.string.Notify_forgot)
                }
                is StateView.Error -> {
                    binding.pbForgot.isVisible = false
                    showSnackBar(FirebaseHelper.validError(stateView.message ?: ""))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}