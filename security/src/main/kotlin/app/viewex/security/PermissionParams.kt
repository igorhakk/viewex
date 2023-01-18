package app.viewex.security

import app.viewex.core.attribute.AttributeAccessor
import app.viewex.core.attribute.Attributes
import app.viewex.core.attribute.PairList

class PermissionParams(
    params: Iterable<Pair<String, Any?>>
) : AttributeAccessor {

    companion object {
        val Empty = PermissionParams(emptyList())
    }

    constructor(params: PairList.() -> Unit) : this(PairList().also(params))

    private val attributes = Attributes(params)

    override val names: Set<String> = attributes.names

    override fun get(name: String): Any? = attributes[name]

    override fun contains(name: String): Boolean = attributes.contains(name)

}
