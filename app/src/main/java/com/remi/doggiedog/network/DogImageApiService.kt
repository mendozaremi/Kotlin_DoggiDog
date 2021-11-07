package com.remi.doggiedog.network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLogginInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Base URL used for API
private const val BASE_URL = "https://dog.ceo/api/breeds/image/"

// Added print out network requests and repsonse in logcat
// Available via the dependency added in build.gradle
private val networkLogginInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingIntercerptor.Level.Body)

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(OkHttpClient.Builder().addInterceptor(networkLogginInterceptor).build())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseurl(BASE_URL)
    .build()

interface DogImageApiService {
    @GET()
}