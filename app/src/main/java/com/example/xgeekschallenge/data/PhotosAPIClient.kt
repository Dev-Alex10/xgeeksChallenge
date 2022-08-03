package com.example.xgeekschallenge.data

import com.example.xgeekschallenge.data.cb.DataRetriever
import com.example.xgeekschallenge.data.model.Photo
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object WebClient {
    /*   val client: PhotosAPI by lazy {
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
    */
    private val apiPhoto by lazy {
        setup()
    }

    private fun setup(): PhotosAPI {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create()
    }

    fun getPhotosList(listener: DataRetriever) {
        apiPhoto.getList().enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    listener.onDataFetchedSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                listener.onDataFetchedFailed()
            }
        })
    }
}