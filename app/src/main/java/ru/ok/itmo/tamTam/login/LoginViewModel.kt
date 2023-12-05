package ru.ok.itmo.tamTam.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.ok.itmo.tamTam.CustomException


class LoginViewModel : ViewModel() {
    private var _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> get() = _loginResult

    fun login(user : User) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = LoginRepository.login(user)
            _loginResult.postValue(result)
        }
    }

    fun reset() {
        _loginResult.value = Result.failure(CustomException)
    }
}
