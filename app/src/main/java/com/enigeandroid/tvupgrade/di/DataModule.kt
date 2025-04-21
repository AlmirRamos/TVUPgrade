package com.enigeandroid.tvupgrade.di

import com.enigeandroid.tvupgrade.data.api.ServiceApi
import com.enigeandroid.tvupgrade.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideServiceProvider() = ServiceProvider()

    @Provides
    fun provideServiceApi(
        serviceProvider: ServiceProvider
    ): ServiceApi {
        return serviceProvider.createService(ServiceApi::class.java)
    }
}