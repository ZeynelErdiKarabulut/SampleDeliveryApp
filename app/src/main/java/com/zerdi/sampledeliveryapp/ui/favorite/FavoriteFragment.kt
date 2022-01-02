package com.zerdi.sampledeliveryapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zerdi.sampledeliveryapp.R
import com.zerdi.sampledeliveryapp.databinding.FragmentRestaurantListingBinding
import com.zerdi.sampledeliveryapp.utils.adapter.FavoriteRestaurantAdapter
import com.zerdi.sampledeliveryapp.utils.listener.IRestaurantOnClick
import androidx.fragment.app.viewModels
import com.zerdi.sampledeliveryapp.model.entity.restaurant.Restaurant
import com.zerdi.sampledeliveryapp.utils.Resource
import com.zerdi.sampledeliveryapp.utils.gone
import com.zerdi.sampledeliveryapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var _binding: FragmentRestaurantListingBinding
    private val viewModel: FavoriteViewModel by viewModels()

    private var restaurantAdapter = FavoriteRestaurantAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantListingBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addObserver()
        addListener()
    }

    private fun initView() {
        _binding.restaurantRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        _binding.pageTitleTextView.text = getString(R.string.favorite_page_title)
    }

    private fun addListener() {
        _binding.previousButton.setOnClickListener {
            findNavController().popBackStack()
        }
        restaurantAdapter.addListener(object : IRestaurantOnClick {
            override fun onClick(restaurant: Restaurant) {
                val action =
                    FavoriteFragmentDirections.actionFavoriteFragmentToRestaurantDetailFragment(
                        restaurant.id
                    )
                findNavController().navigate(action)
            }
        })
    }

    private fun addObserver() {
        viewModel.getFavoriteRestaurantList().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> _binding.progressBar.show()
                Resource.Status.SUCCESS -> setRestaurant(response.data?.restaurantList)
                Resource.Status.ERROR -> isRestaurantListVisible(false)
            }
        })
    }

    private fun setRestaurant(restaurantList: List<Restaurant>?) {
        val isVisible = !restaurantList.isNullOrEmpty()
        isRestaurantListVisible(isVisible)
        restaurantAdapter.setData(restaurantList)
        _binding.restaurantRecyclerView.adapter = restaurantAdapter
    }

    private fun isRestaurantListVisible(isVisible: Boolean) {
        _binding.progressBar.gone()
        _binding.restaurantRecyclerView.isVisible = isVisible
        _binding.responseErrorLinearLayout.isVisible = isVisible.not()
    }

}