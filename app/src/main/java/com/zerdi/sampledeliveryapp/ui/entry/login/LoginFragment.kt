package com.zerdi.sampledeliveryapp.ui.entry.login

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zerdi.sampledeliveryapp.ui.MainActivity
import com.zerdi.sampledeliveryapp.databinding.FragmentLoginBinding
import com.zerdi.sampledeliveryapp.model.entity.login.LoginRequest
import com.zerdi.sampledeliveryapp.utils.Resource
import com.zerdi.sampledeliveryapp.utils.gone
import com.zerdi.sampledeliveryapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(){
    private lateinit var _binding : FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    private fun addListener() {
        _binding.loginButton.setOnClickListener {
            login()
        }
        _binding.loginAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }
        })
    }

    private fun login() {
        val email = _binding.emailTextView.editText?.text.toString()
        val password = _binding.passwordTextView.editText?.text.toString()
        val request = LoginRequest(email,password)
        viewModel.login(request).observe(viewLifecycleOwner, { response ->
            if(response.status == Resource.Status.SUCCESS) {
                _binding.containerLinearLayout.gone()
                _binding.loginAnimation.show()
            }
        })

    }
}