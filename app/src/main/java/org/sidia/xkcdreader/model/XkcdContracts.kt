package org.sidia.xkcdreader.model

import java.io.InputStream

interface XkcdXmlFeedParserContract {

    fun parse(inputStream: InputStream): List<XkcdPost>

}

interface XkcdRepositoryContract {

    fun getFeed(): List<XkcdPost>

}