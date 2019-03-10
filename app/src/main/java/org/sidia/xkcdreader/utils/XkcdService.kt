package org.sidia.xkcdreader.utils

import org.sidia.xkcdreader.model.XkcdPost
import retrofit2.Call
import retrofit2.http.GET

interface XkcdService {
    @GET("info.0.json")
    fun fetchLastComic(): Call<XkcdPost>
}