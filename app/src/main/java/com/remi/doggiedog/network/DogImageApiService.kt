package com.remi.doggiedog.network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Base URL used for API
private const val BASE_URL = "https://dog.ceo/api/breeds/image/"

// Added print out network requests and repsonse in logcat
// Available via the dependency added in build.gradle
private val networkLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

// Moshi will break down response to Json for usable data
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit client print ok status code add json data moshi
private val retrofit = Retrofit.Builder()
    .client(OkHttpClient.Builder().addInterceptor(networkLoggingInterceptor).build())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Interface store
interface DogImageApiService {
    // add "random" to the BASE_URL return one Dog image
    @GET("random")
    suspend fun getRandomDogImage(): DogImage
}

//
object DogImageApi {
    val retrofitService: DogImageApiService by lazy { retrofit.create(DogImageApiService::class.java)}
}