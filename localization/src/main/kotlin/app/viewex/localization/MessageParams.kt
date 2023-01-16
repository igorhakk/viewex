package app.viewex.app.localization

import app.viewex.core.attribute.AttributeAccessor
import app.viewex.core.attribute.Attributes
import app.viewex.core.attribute.PairList

class MessageParams(
    params: Iterable<Pair<String, Any?>>
) : AttributeAccessor {

    constructor(params: PairList.() -> Unit) : this(PairList().also(params))

    companion object {
        val Empty = MessageParams(emptyList())
    }

    private val attributes = Attributes(params)

    override val names: Set<String> = attributes.names

    override fun get(name: String): Any? = attributes[name]

    override fun contains(name: String): Boolean = attributes.contains(name)

    override fun iterator(): Iterator<Pair<String, Any?>> = attributes.iterator()

}
