package com.zerdi.sampledeliveryapp.ui.entry.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zerdi.sampledeliveryapp.model.ApiRepository
import com.zerdi.sampledeliveryapp.model.entity.login.LoginResponse
import com.zerdi.sampledeliveryapp.model.entity.login.RegisterRequest
import com.zerdi.sampledeliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun signup(request: RegisterRequest): LiveData<Resource<LoginResponse>> =
        apiRepository.register(request)
}