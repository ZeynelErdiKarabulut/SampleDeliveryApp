package com.zerdi.sampledeliveryapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zerdi.sampledeliveryapp.R
import com.zerdi.sampledeliveryapp.databinding.FragmentOnboardingBinding

class DeliveryOnBoardingFragment : Fragment() {
    private lateinit var _binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.contentImageView.setImageResource(R.drawable.onboarding_delivery)
        _binding.contentTextView.text = getString(R.string.onboarding_delivery_text)
    }
}