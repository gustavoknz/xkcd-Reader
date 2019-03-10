package org.sidia.xkcdreader

//https://stackoverflow.com/questions/45841067/what-is-the-best-way-to-define-log-tag-constant-in-kotlin
val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }
