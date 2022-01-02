package com.zerdi.sampledeliveryapp.ui.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zerdi.sampledeliveryapp.model.ApiRepository
import com.zerdi.sampledeliveryapp.model.entity.profile.UserRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getUserDetail() = apiRepository.getUserDetail()
    fun updateUser(request: UserRequest) = apiRepository.updateUser(request)
    fun logout() {
        apiRepository.logout()
    }

}