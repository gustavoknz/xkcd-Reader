package org.sidia.xkcdreader.model

data class XkcdPost(
    val title: String,
    val link: String,
    val updated: String,
    val id: String,
    val imageUrl: String,
    val description: String
)