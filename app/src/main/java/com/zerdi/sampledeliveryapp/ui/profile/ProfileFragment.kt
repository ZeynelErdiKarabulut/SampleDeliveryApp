package com.zerdi.sampledeliveryapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zerdi.sampledeliveryapp.R
import com.zerdi.sampledeliveryapp.databinding.FragmentProfileBinding
import com.zerdi.sampledeliveryapp.model.entity.profile.User
import com.zerdi.sampledeliveryapp.model.entity.profile.UserRequest
import com.zerdi.sampledeliveryapp.ui.splash.SplashActivity
import com.zerdi.sampledeliveryapp.utils.Resource
import com.zerdi.sampledeliveryapp.utils.gone
import com.zerdi.sampledeliveryapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var _binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addListener()
    }

    private fun addListener() {
        _binding.saveButton.setOnClickListener { updateUserInformation() }
        _binding.closeImageButton.setOnClickListener { findNavController().popBackStack() }
        _binding.logoutButton.setOnClickListener {
            viewModel.logout()
            val intent = Intent(context, SplashActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun updateUserInformation() {
        val request = UserRequest(
            name = _binding.nameLayout.fieldEditText.text.toString(),
            phoneNumber = _binding.phoneLayout.fieldEditText.text.toString(),
            address = _binding.addressLayout.fieldEditText.text.toString(),
            password = if (_binding.passwordLayout.fieldEditText.text.isNullOrEmpty())
                null
            else
                _binding.passwordLayout.fieldEditText.text.toString()
        )
        viewModel.updateUser(request).observe(viewLifecycleOwner, { response ->
            if (response.status == Resource.Status.SUCCESS)
                findNavController().popBackStack()
        })
    }

    private fun initView() {
        viewModel.getUserDetail().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> _binding.progressBar.show()
                Resource.Status.SUCCESS -> response.data?.let { setFields(it) }
                Resource.Status.ERROR -> _binding.progressBar.gone()
            }
        })
    }

    private fun setFields(user: User) {
        _binding.progressBar.gone()
        _binding.containerLinearLayout.show()

        val options = RequestOptions().placeholder(R.drawable.ic_profile)
        Glide.with(_binding.profileImageView.context)
            .applyDefaultRequestOptions(options)
            .load(user.imageUrl).into(_binding.profileImageView)

        _binding.mailTextView.text = user.mail

        _binding.phoneLayout.fieldImageButton.setBackgroundResource(R.drawable.ic_phone)
        _binding.phoneLayout.fieldEditText.inputType = InputType.TYPE_CLASS_PHONE
        _binding.phoneLayout.titleTextView.text = getString(R.string.phone_number_title)
        _binding.phoneLayout.fieldEditText.hint = getString(R.string.hint_phone)
        _binding.phoneLayout.fieldEditText.setText(user.phoneNumber)

        _binding.nameLayout.fieldImageButton.setBackgroundResource(R.drawable.ic_name)
        _binding.nameLayout.titleTextView.text = getString(R.string.name_title)
        _binding.nameLayout.fieldEditText.hint = getString(R.string.hint_name)
        _binding.nameLayout.fieldEditText.setText(user.name)

        _binding.addressLayout.fieldImageButton.setBackgroundResource(R.drawable.ic_location)
        _binding.addressLayout.fieldEditText.setText(user.address)
        _binding.addressLayout.titleTextView.text = getString(R.string.address_title)
        _binding.addressLayout.fieldEditText.hint = getString(R.string.hint_address)

        _binding.passwordLayout.fieldImageButton.setBackgroundResource(R.drawable.ic_password)
        _binding.passwordLayout.titleTextView.text = getString(R.string.password_title)
        _binding.passwordLayout.fieldEditText.hint = getString(R.string.hint_password)
    }
}