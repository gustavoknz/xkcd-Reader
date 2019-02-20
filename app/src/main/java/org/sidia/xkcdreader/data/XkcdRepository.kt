package org.sidia.xkcdreader.data

import org.sidia.xkcdreader.model.XkcdPost
import org.sidia.xkcdreader.model.XkcdRepositoryContract

class XkcdRepository: XkcdRepositoryContract {

    override fun getFeed(): List<XkcdPost> {
        return emptyList()
    }

}