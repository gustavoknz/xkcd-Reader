package org.sidia.xkcdreader.data

import com.einmalfel.earl.EarlParser
import org.sidia.xkcdreader.model.XkcdPost
import org.sidia.xkcdreader.model.XkcdXmlFeedParserContract
import java.io.InputStream


class XkcdXmlFeedParser : XkcdXmlFeedParserContract {

    override fun parse(inputStream: InputStream): List<XkcdPost> {
        val feed = EarlParser.parseOrThrow(inputStream, 0)
        return emptyList()
    }

}