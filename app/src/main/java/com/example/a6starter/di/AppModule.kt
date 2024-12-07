package com.example.a6starter.di

import com.example.a6starter.data.remote.Api
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * This is the AppModule. This handles dependency injection with Dagger Hilt for you, so you do not
 * need to modify this file.
 *
 * Essentially what it does is provide a Singleton instance of a Moshi adapter and a Singleton
 * instance of MyApi so they can be used throughout the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideMyApi(moshi: Moshi): Api {
        // FIXME - Change Base URL
        return Retrofit.Builder()
            .baseUrl("http://34.86.243.184:8000/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(Api::class.java)
    }
}