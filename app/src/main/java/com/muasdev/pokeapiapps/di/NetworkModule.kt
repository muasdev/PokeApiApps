package com.muasdev.pokeapiapps.di

import com.muasdev.pokeapiapps.data.contants.AppConstants
import com.muasdev.pokeapiapps.data.services.PokeServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PokeApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OwnApi


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @PokeApi
    @Singleton
    @Provides
    fun providePokeApiRetrofitService(okHttpClient: OkHttpClient): PokeServices =
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL_POKE_API)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(okHttpClient)
            .build()
            .create(PokeServices::class.java)

    @OwnApi
    @Singleton
    @Provides
    fun provideOwnApiRetrofitService(okHttpClient: OkHttpClient): PokeServices =
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL_OWN_API)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(okHttpClient)
            .build()
            .create(PokeServices::class.java)

}