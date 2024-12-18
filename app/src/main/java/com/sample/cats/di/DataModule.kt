package com.sample.cats.di

import com.google.gson.Gson
import com.sample.cats.data.ErrorHandler
import com.sample.cats.data.RepositoryImpl
import com.sample.cats.data.local.DataStoreManager
import com.sample.cats.data.remote.RemoteDataSource
import com.sample.cats.data.remote.RemoteDataSourceImpl
import com.sample.cats.data.remote.connection.MService
import com.sample.cats.domain.Repository
import com.sample.cats.domain.exceptions.IErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSourceImpl: RemoteDataSourceImpl,
        dataStoreManager: DataStoreManager
    ): Repository {
        return RepositoryImpl(remoteDataSourceImpl, dataStoreManager)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        service: MService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(service)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideErrorHandler(): IErrorHandler {
        return ErrorHandler()
    }
}