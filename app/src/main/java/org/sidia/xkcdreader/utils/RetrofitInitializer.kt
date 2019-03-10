package org.sidia.xkcdreader.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun xkcdService(): XkcdService = retrofit.create(XkcdService::class.java)
}
