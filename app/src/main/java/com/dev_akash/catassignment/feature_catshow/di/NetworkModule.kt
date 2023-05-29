package com.dev_akash.catassignment.feature_catshow.di

import com.dev_akash.catassignment.utils.network.AuthInterceptor
import com.dev_akash.catassignment.utils.network.NetworkConnectionInterceptor
import com.dev_akash.catassignment.feature_catshow.data.remote.CatApi
import com.dev_akash.catassignment.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun providesCatsApi(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): CatApi {
        return retrofitBuilder.client(okHttpClient).build().create(CatApi::class.java)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        authInterceptor: AuthInterceptor, connectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(connectionInterceptor)
            .addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
}