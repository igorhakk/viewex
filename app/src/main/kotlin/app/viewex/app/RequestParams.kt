package app.viewex.app

import app.viewex.core.attribute.AttributeAccessor
import app.viewex.core.attribute.Attributes
import app.viewex.core.attribute.asString

class RequestParams(
    params: Iterable<Pair<String, Any?>>
) : AttributeAccessor {

    companion object {
        val Empty = RequestParams(emptyList())
    }

    private val attributes = Attributes(params)

    override val names: Set<String> = attributes.names

    override fun get(name: String): Any? = attributes[name]

    override fun contains(name: String): Boolean = attributes.contains(name)

    override fun toString(): String = asString()
}
