package com.zerdi.sampledeliveryapp.ui.entry.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zerdi.sampledeliveryapp.model.ApiRepository
import com.zerdi.sampledeliveryapp.model.entity.login.LoginRequest
import com.zerdi.sampledeliveryapp.model.entity.login.LoginResponse
import com.zerdi.sampledeliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun login(request: LoginRequest): LiveData<Resource<LoginResponse>> =
        apiRepository.login(request)

}