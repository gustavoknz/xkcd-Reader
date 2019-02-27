package org.sidia.xkcdreader.model

import java.io.InputStream

interface XkcdXmlFeedParserContract {

    fun parse(inputStream: InputStream): List<XkcdPost>
    fun parseLast(inputStream: InputStream): XkcdPost

}

interface XkcdRepositoryContract {

    suspend fun getFeed(): List<XkcdPost>
    suspend fun getLast(): XkcdPost

}
