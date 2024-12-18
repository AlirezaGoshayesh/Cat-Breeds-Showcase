package com.sample.cats.di

import android.content.Context
import android.util.Log
import com.sample.cats.BuildConfig
import com.sample.cats.R
import com.sample.cats.data.remote.connection.TokenInterceptor
import com.sample.cats.data.remote.connection.MService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(tokenInterceptor)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message: String? ->
            if (message != null)
                Log.d(
                    "network_log",
                    message
                )
        }.setLevel(HttpLoggingInterceptor.Level.BODY)


    @Singleton
    @Provides
    fun provideMService(
        okHttpClient: OkHttpClient,
        @ApplicationContext mContext: Context,
        gsonConverterFactory: GsonConverterFactory
    ): MService {
        val retrofit = Retrofit.Builder()
            .baseUrl(mContext.getString(R.string.base_url))
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
        return retrofit.create(MService::class.java)
    }


}