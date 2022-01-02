package com.zerdi.sampledeliveryapp.utils.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zerdi.sampledeliveryapp.ui.onboarding.DeliveryOnBoardingFragment
import com.zerdi.sampledeliveryapp.ui.onboarding.FoodOnboardingFragment
import com.zerdi.sampledeliveryapp.ui.onboarding.MenuOnboardingFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> MenuOnboardingFragment()
            1 -> DeliveryOnBoardingFragment()
            else -> FoodOnboardingFragment()
        }
    }
}