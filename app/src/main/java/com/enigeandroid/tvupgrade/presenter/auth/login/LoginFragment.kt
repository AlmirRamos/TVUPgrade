package com.enigeandroid.tvupgrade.presenter.auth.login


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.hellodev.netflix.util.FirebaseHelper
import com.bumptech.glide.Glide
import com.enigeandroid.tvupgrade.R
import com.enigeandroid.tvupgrade.databinding.FragmentLoginBinding
import com.enigeandroid.tvupgrade.presenter.main.activity.MainActivity
import com.enigeandroid.tvupgrade.util.StateView
import com.enigeandroid.tvupgrade.util.hideKeyboard
import com.enigeandroid.tvupgrade.util.initToolbar
import com.enigeandroid.tvupgrade.util.isEmailValid
import com.enigeandroid.tvupgrade.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel : LoginViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar( toolbar = binding.toolbarLogin)
        initListener()
    }

    private fun initListener() {

        binding.btnForgotLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }

        binding.btnCriarContaLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnEntrarLogin.setOnClickListener {
            validateData()
        }
        Glide
            .with(requireContext())
            .load(R.drawable.loading)
            .into(binding.pbLogin)
    }

    private fun validateData () {
        val email = binding.editEmailLogin.text.toString()
        val password = binding.editSenhaLogin.text.toString()
        if(email.isEmailValid()) {
            if (password.isNotEmpty()) {
                hideKeyboard()
                login(email, password)
            }else{
                showSnackBar(R.string.text_password_empty_login_fragment)
            }
        }else {
            showSnackBar(R.string.text_email_empty_login_fragment)
        }
    }

    private fun login(email: String, password: String) {
        viewModel.login(email, password).observe(viewLifecycleOwner) { stateView ->
            when(stateView) {
                is StateView.Loading -> {
                    binding.pbLogin.isVisible = true
                }
                is StateView.Success -> {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                    showSnackBar(R.string.text_email_bem_vindo)
                }
                is StateView.Error -> {
                    binding.pbLogin.isVisible = false
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