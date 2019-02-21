package org.sidia.xkcdreader.data

import com.einmalfel.earl.EarlParser
import org.sidia.xkcdreader.model.XkcdPost
import org.sidia.xkcdreader.model.XkcdXmlFeedParserContract
import java.io.InputStream


class XkcdXmlFeedParser : XkcdXmlFeedParserContract {

    private val regex = """="([^"]+)""".toRegex()

    override fun parse(inputStream: InputStream): List<XkcdPost> {
        val feed = EarlParser.parseOrThrow(inputStream, 0)
        return feed.items.map {
            val imageAndTitle = extractImageContents(it.description ?: "")
            XkcdPost(
                it.title ?: "",
                it.link ?: "",
                it.publicationDate?.toString() ?: "",
                it.id ?: "",
                imageAndTitle.first,
                imageAndTitle.second)
        }
    }

    private fun extractImageContents(image: String): Pair<String, String> {
        val matches = regex.findAll(image)
        val groups = matches.toList()
        if (groups.size < 3) {
            return "" to ""
        }
        return groups[0].value to groups[1].value
    }

}