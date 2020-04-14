package com.victor.coffeeshop_kotlin.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.victor.coffeeshop_kotlin.network.service.OpenApiService
import com.victor.coffeeshop_kotlin.persistence.AppDatabase
import com.victor.coffeeshop_kotlin.persistence.dao.AddressDao
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeDao
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeShopDao
import com.victor.coffeeshop_kotlin.session.NetworkStatus
import com.victor.coffeeshop_kotlin.session.SessionManager
import com.victor.coffeeshop_kotlin.util.COFFEE_SHOP_PLACES_DATABASE_NAME
import com.victor.coffeeshop_kotlin.util.LiveDataCallAdapterFactory
import com.victor.coffeeshop_kotlin.util.RETROFIT_BASE_URL
import com.victor.coffeeshop_kotlin.util.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    /**provides the connection to API*/
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RETROFIT_BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideOpenApiService(retrofit: Retrofit): OpenApiService {
        return retrofit.create(OpenApiService::class.java)
    }

    /**provides the creation of database*/
    @Singleton
    @Provides
    fun provideDatabaseInstance(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application, AppDatabase::class.java, COFFEE_SHOP_PLACES_DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideCoffeeShopDao(database: AppDatabase): CoffeeShopDao {
        return database.getCoffeeShopDao()
    }

    @Singleton
    @Provides
    fun provideCoffeeDao(database: AppDatabase): CoffeeDao {
        return database.getCoffeeDao()
    }

    @Singleton
    @Provides
    fun provideAddressDao(database: AppDatabase): AddressDao {
        return database.getAddressDao()
    }

    /**provides de location of the user*/
    @Singleton
    @Provides
    fun providesFusedLocationProviderClient(application: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application)
    }

    @Singleton
    @Provides
    fun provideTaskLocation(client: FusedLocationProviderClient): Task<Location> {
        return client.lastLocation
    }

    /**provides shared preferes*/
    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideSharedPreferencesEditor(pref: SharedPreferences): SharedPreferences.Editor {
        return pref.edit()
    }


    /**provides de session manager */
    @Singleton
    @Provides
    fun provideSessionManager(
        connection: NetworkStatus
    ): SessionManager {
        return SessionManager(connection)
    }

    /**provides de connection of internet */
    @Singleton
    @Provides
    fun provideOnConnection(application: Application): NetworkStatus {
        return NetworkStatus(application)
    }

    /**provides glide*/


}