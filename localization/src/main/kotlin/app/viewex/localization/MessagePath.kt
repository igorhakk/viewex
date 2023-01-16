package app.viewex.app.localization

import app.viewex.app.IdentifiedPath

class MessagePath(
    items: Iterable<Item>
) : IdentifiedPath(items) {
    constructor(vararg raw: String) : this(parseItems(*raw))
}

fun MessagePath.plus(
    vararg rawItem: String
) : MessagePath = plus(*IdentifiedPath.parseItems(*rawItem).toTypedArray())

fun MessagePath.plus(
    vararg item: IdentifiedPath.Item
) : MessagePath = plus(item.toList())

fun MessagePath.plus(
    items: Iterable<IdentifiedPath.Item>
) : MessagePath = MessagePath(this.items.plus(items))
