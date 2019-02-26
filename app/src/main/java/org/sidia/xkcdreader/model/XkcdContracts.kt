package org.sidia.xkcdreader.model

import java.io.InputStream

class Constants {
    companion object {
        const val FEED_RSS_URL = "https://xkcd.com/rss.xml"
    }
}

interface XkcdXmlFeedParserContract {

    fun parse(inputStream: InputStream): List<XkcdPost>

}

interface XkcdRepositoryContract {

    suspend fun getFeed(): List<XkcdPost>

}