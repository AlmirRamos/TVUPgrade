package com.enigeandroid.tvupgrade.presenter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.enigeandroid.tvupgrade.R
import com.enigeandroid.tvupgrade.databinding.ActivityMainBinding
import com.enigeandroid.tvupgrade.presenter.auth.forgot.ForgotFragment
import com.enigeandroid.tvupgrade.presenter.auth.login.LoginFragment
import com.enigeandroid.tvupgrade.presenter.auth.register.RegisterFragment
import com.enigeandroid.tvupgrade.presenter.onboard.OnboardFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContentView(binding.root)

       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val registerFragment = RegisterFragment()
        val loginFragment = LoginFragment()
        val forgotFragment = ForgotFragment()
        val onboardFragment = OnboardFragment()

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.container, forgotFragment)
        transaction.commit()

    }
}