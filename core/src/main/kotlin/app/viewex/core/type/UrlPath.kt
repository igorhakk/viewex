package app.viewex.core.type

open class UrlPath(
    pathItems: Iterable<Item>
) : PathType.Abstract<UrlPath.Item>(pathItems, Separator) {

    companion object {
        const val Separator = "/"

        fun parse(vararg rawItems: String): UrlPath = UrlPath(parseItems(*rawItems))

        fun parseItems(vararg rawItems: String): Iterable<Item> =
            rawItems.flatMap {
                it.split(Separator).map { item ->
                    Item.parse(item)
                }
            }
    }

    open class Item protected constructor(
        name: Any? = null
    ) : PathType.Item, StringType(name, 1, 30, "[a-zA-Z\\.\\-\\_]") {

        companion object {
            val Empty = Item()

            fun parseOrEmpty(name: Any?): Item = parseOrNull(name) ?: Empty

            fun parseOrNull(name: Any?): Item? = name?.let { parse(it) }

            fun parse(name: Any): Item = Item(name)
        }
    }
}
