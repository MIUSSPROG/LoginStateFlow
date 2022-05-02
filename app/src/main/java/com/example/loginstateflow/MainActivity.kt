package com.example.loginstateflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.loginstateflow.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.etUsername.text.toString(), binding.etPassword.text.toString())
        }

        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect{
                when(it){
                    is MainViewModel.LoginUiState.Success -> {
                        Snackbar.make(binding.root, "Successfully logged in", Snackbar.LENGTH_LONG).show()
                        binding.progressBar.isVisible = false
                    }
                    is MainViewModel.LoginUiState.Error -> {
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                        binding.progressBar.isVisible = false
                    }
                    is MainViewModel.LoginUiState.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }

//        viewModel.loginUiState.observe(this){
//            when(it){
//                is MainViewModel.LoginUiState.Success -> {
//                    Snackbar.make(binding.root, "Successfully logged in", Snackbar.LENGTH_LONG).show()
//                    binding.progressBar.isVisible = false
//                }
//                is MainViewModel.LoginUiState.Error -> {
//                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
//                        binding.progressBar.isVisible = false
//                }
//                is MainViewModel.LoginUiState.Loading -> {
//                        binding.progressBar.isVisible = true
//                }
//            }
//        }

//        lifecycleScope.launchWhenStarted {
//            viewModel.loginUiState.collect{
//                when(it){
//                    is MainViewModel.LoginUiState.Success -> {
//                        Snackbar.make(binding.root, "Successfully logged in", Snackbar.LENGTH_LONG).show()
//                        binding.progressBar.isVisible = false
//                    }
//                    is MainViewModel.LoginUiState.Error -> {
//                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
//                        binding.progressBar.isVisible = false
//                    }
//                    is MainViewModel.LoginUiState.Loading -> {
//                        binding.progressBar.isVisible = true
//                    }
//                    else -> Unit
//                }
//            }
//        }
    }
}