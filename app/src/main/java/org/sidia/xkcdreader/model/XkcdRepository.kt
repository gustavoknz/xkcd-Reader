package org.sidia.xkcdreader.model

import java.net.URL

class XkcdRepository(private val xmlFeedParserContract: XkcdXmlFeedParserContract) : XkcdRepositoryContract {

    override suspend fun getFeed(): List<XkcdPost> {
        return URL(Constants.FEED_RSS_URL).openConnection().getInputStream().use {
            xmlFeedParserContract.parse(it)
        }
    }

}