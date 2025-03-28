package com.enigeandroid.tvupgrade.presenter.auth.register

import android.content.Intent
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
import com.enigeandroid.tvupgrade.databinding.FragmentRegisterBinding
import com.enigeandroid.tvupgrade.presenter.main.activity.MainActivity
import com.enigeandroid.tvupgrade.util.StateView
import com.enigeandroid.tvupgrade.util.hideKeyboard
import com.enigeandroid.tvupgrade.util.initToolbar
import com.enigeandroid.tvupgrade.util.isEmailValid
import com.enigeandroid.tvupgrade.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel : RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar( toolbar = binding.toolbarRegister)
        initListener()
    }

    private fun initListener() {
        binding.btnInscrevaseRegister.setOnClickListener {
            validateData()
        }

        Glide
            .with(requireContext())
            .load(R.drawable.loading)
            .into(binding.pbRegister)
    }

    private fun validateData () {
        val email = binding.editEmailRegister.text.toString()
        val password = binding.editSenhaRegister.text.toString()

        if(email.isEmailValid()) {
            if (password.isNotEmpty()) {
                hideKeyboard()
                register(email, password)
            }else{
                showSnackBar(R.string.text_password_empty)
            }
        }else {
            showSnackBar(R.string.text_email_empty)
        }
    }

    private fun register(email: String, password: String) {
        viewModel.register(email, password).observe(viewLifecycleOwner) { stateView ->
            when(stateView) {
                is StateView.Loading -> {
                    binding.pbRegister.isVisible = true
                }
                is StateView.Success -> {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                    showSnackBar(R.string.text_sucesso_register)
                }
                is StateView.Error -> {
                    binding.pbRegister.isVisible = false
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