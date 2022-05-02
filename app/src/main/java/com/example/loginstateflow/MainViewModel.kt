package com.example.loginstateflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

//    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
//    val loginUiState: StateFlow<LoginUiState> = _loginUiState
//    private val _loginUiState = MutableLiveData<LoginUiState>()
//    val loginUiState: LiveData<LoginUiState> = _loginUiState

    private val eventChannel = Channel<LoginUiState>()
    val eventFlow = eventChannel.receiveAsFlow()


    fun login(username: String, password: String) = viewModelScope.launch {
//        _loginUiState.value = LoginUiState.Loading
        eventChannel.send(LoginUiState.Loading)
        delay(2000L)
        if (username == "android" && password == "topsecret"){
            eventChannel.send(LoginUiState.Success)
//            _loginUiState.value = LoginUiState.Success
//            _loginUiState.value = LoginUiState.Success
        }else{
            eventChannel.send(LoginUiState.Error("Wrong credentials"))
//            _loginUiState.value = LoginUiState.Error("Wrong credentials")
//            _loginUiState.value = LoginUiState.Error("Wrong credentials")
        }

    }

    sealed class LoginUiState{
        object Success: LoginUiState()
        data class Error(val message: String): LoginUiState()
        object Loading: LoginUiState()
        object Empty: LoginUiState()
    }

}