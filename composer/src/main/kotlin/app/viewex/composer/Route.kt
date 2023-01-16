package app.viewex.composer

import app.viewex.core.type.UrlPath

class Route(
    val path: UrlPath,
    val query: LayoutQuery
) {

    companion object {
        const val PathParam = "path"
        const val QueryParam = "query"
    }

    fun findItem(
        index: Int
    ): String? = path.items.getOrNull(index)?.value

}
