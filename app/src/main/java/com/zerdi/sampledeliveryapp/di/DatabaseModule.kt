package com.zerdi.sampledeliveryapp.di

import android.content.Context
import com.zerdi.sampledeliveryapp.model.local.LocalDataSource
import com.zerdi.sampledeliveryapp.model.local.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(
    SingletonComponent::class
)
class DatabaseModule {

    @Singleton
    @Provides
    fun sharedPrefManager(@ApplicationContext context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }

    @Singleton
    @Provides
    fun localDataSource(sharedPrefManager: SharedPrefManager): LocalDataSource {
        return LocalDataSource(sharedPrefManager)
    }
}