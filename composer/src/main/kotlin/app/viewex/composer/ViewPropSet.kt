package app.viewex.composer

import app.viewex.core.attribute.AttributeAccessor
import app.viewex.core.attribute.Attributes
import app.viewex.core.attribute.asString

class ViewPropSet(
    props: Iterable<Prop>
) : AttributeAccessor {

    companion object {
        val Empty = ViewPropSet(emptyList())
    }

    private val attributes = Attributes(props.map { Pair(it.name, it.value) })

    override val names: Set<String> = attributes.names

    override fun get(name: String): Any? = attributes[name]

    override fun contains(name: String): Boolean = attributes.contains(name)

    override fun toString(): String = asString()

    class Prop(
        val name: String,
        val value: Any?
    )

}
