package org.sidia.xkcdreader.utils

import org.sidia.xkcdreader.model.XkcdPost
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface XkcdService {
    @GET("info.0.json")
    fun fetchLastComic(): Call<XkcdPost>

    @GET("{id}/info.0.json")
    fun fetchComic(@Path("id") id: Int): Call<XkcdPost>
}