package com.zerdi.sampledeliveryapp.model

import com.zerdi.sampledeliveryapp.model.entity.login.LoginRequest
import com.zerdi.sampledeliveryapp.model.entity.basket.BasketRequest
import com.zerdi.sampledeliveryapp.model.entity.login.RegisterRequest
import com.zerdi.sampledeliveryapp.model.entity.profile.UserRequest
import com.zerdi.sampledeliveryapp.model.local.LocalDataSource
import com.zerdi.sampledeliveryapp.model.remote.RemoteDataSource
import com.zerdi.sampledeliveryapp.utils.performAuthTokenNetworkOperation
import com.zerdi.sampledeliveryapp.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
) {
    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.login(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun register(request: RegisterRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.register(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun getRestaurantList() = performNetworkOperation {
        remoteDataSource.getRestaurantList()
    }

    fun getBasketItemList() = performNetworkOperation {
        remoteDataSource.getBasketItemList()
    }

    fun buyBasket() = performNetworkOperation {
        remoteDataSource.buyBasket()
    }

    fun getFavoriteRestaurantList() = performNetworkOperation {
        remoteDataSource.getFavoriteRestaurantList()
    }

    fun getLastOrders() = performNetworkOperation {
        remoteDataSource.getLastOrders()
    }

    fun rateOrder(mealId: Int, vote: Float, cartId: Int) = performNetworkOperation {
        remoteDataSource.rateOrder(mealId, vote, cartId)
    }

    fun getUserDetail() = performNetworkOperation {
        remoteDataSource.getUserDetail()
    }

    fun getUserImage() = performNetworkOperation {
        remoteDataSource.getUserImage()
    }

    fun updateUser(request: UserRequest) = performNetworkOperation {
        remoteDataSource.updateUser(request)
    }

    fun getRestaurantById(restaurantId: Int) = performNetworkOperation {
        remoteDataSource.getRestaurantById(restaurantId)
    }

    fun addFavoriteRestaurant(restaurantId: Int) = performNetworkOperation {
        remoteDataSource.addFavoriteRestaurant(restaurantId)
    }

    fun removeFavoriteRestaurant(restaurantId: Int) = performNetworkOperation {
        remoteDataSource.removeFavoriteRestaurant(restaurantId)
    }

    fun getRestaurantsByCuisine(cuisineId: Int) = performNetworkOperation {
        remoteDataSource.getRestaurantsByCuisine(cuisineId)
    }

    //Cuisines
    fun getCuisineList() = performNetworkOperation {
        remoteDataSource.getCuisineList()
    }

    //Meal
    fun getMealById(mealId: Int) = performNetworkOperation {
        remoteDataSource.getMealById(mealId)
    }

    fun removeItemFromBasket(mealId: Int) = performNetworkOperation {
        remoteDataSource.removeItemFromBasket(mealId)
    }

    fun addBasket(request: BasketRequest) = performNetworkOperation {
        remoteDataSource.addBasket(request)
    }

    fun logout() {
        localDataSource.saveToken("")
    }

}