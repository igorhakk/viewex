package app.viewex.composer

import app.viewex.core.attribute.AttributeAccessor
import app.viewex.core.attribute.Attributes

class LayoutQuery(
    params: Iterable<Pair<String, Any?>>
) : AttributeAccessor {

    companion object {
        val Empty = LayoutQuery(emptyList())
    }

    private val attributes = Attributes(params)

    override val names: Set<String> = attributes.names

    override fun get(name: String): Any? = attributes[name]

    override fun contains(name: String): Boolean = attributes.contains(name)
}
