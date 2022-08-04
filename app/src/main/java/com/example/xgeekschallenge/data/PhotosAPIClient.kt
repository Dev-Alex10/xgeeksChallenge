package com.example.xgeekschallenge.data

import android.util.Log
import com.example.xgeekschallenge.data.cb.DataRetriever
import com.example.xgeekschallenge.data.model.PhotosSearchResponse
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TAG = "WEBCLIENT"

object WebClient {
    val client: PhotosAPI by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                )
            )
            .client(
                OkHttpClient.Builder().connectTimeout(
                    CONNECTION_TIMEOUT_MS,
                    TimeUnit.SECONDS
                ).addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create(PhotosAPI::class.java)
    }
/*
    private val apiPhoto by lazy {
        setup()
    }

    private fun setup(): PhotosAPI {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create()
    }

    fun getPhotosList(listener: DataRetriever) {
        apiPhoto.getList().enqueue(object : Callback<List<PhotosSearchResponse>> {
            override fun onResponse(
                call: Call<List<PhotosSearchResponse>>,
                response: Response<List<PhotosSearchResponse>>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, "$response")
                    listener.onDataFetchedSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<PhotosSearchResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                listener.onDataFetchedFailed()
            }
        })
    }*/
}