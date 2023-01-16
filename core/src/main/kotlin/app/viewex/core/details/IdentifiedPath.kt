package app.viewex.core.details

import app.viewex.core.type.PathType
import app.viewex.core.type.StringType

abstract class IdentifiedPath(
    items: Iterable<Item>
) : PathType.Abstract<IdentifiedPath.Item>(items) {

    companion object {

        fun parseItems(
            vararg raw: String,
            pattern: String = Item.Pattern,
            itemLength: Int = Item.Length
        ): List<Item> = raw.flatMap {
            it.split(pattern).map { item ->
                Item.parse(item, itemLength)
            }
        }

    }

    open class Item protected constructor(
        name: Any? = null,
        length: Int = Length
    ) : PathType.Item, StringType(name, 1, length, Pattern) {

        companion object {
            const val Pattern = "[0-9A-Za-z\\-\\_]*"
            const val Length = 30

            val Empty = Item()

            fun parseOrEmpty(name: Any?, length: Int = Length): Item = parseOrNull(name, length) ?: Empty

            fun parseOrNull(name: Any?, length: Int = Length): Item? = name?.let { parse(name, length) }

            fun parse(name: Any, length: Int = Length): Item = Item(name, length)
        }

    }

    abstract class Named<Name : ObjectName>(
        items: Iterable<Item>
    ) : IdentifiedPath(items), app.viewex.core.details.Named<Name>

}
