package app.viewex.composer.event

import app.viewex.core.attribute.AttributeAccessor
import app.viewex.core.attribute.Attributes
import app.viewex.core.attribute.PairList
import app.viewex.core.attribute.asString

class EventData(
    props: Iterable<Pair<String, Any?>>
) : AttributeAccessor {

    constructor(props: PairList.() -> Unit) : this(PairList().also(props))

    companion object {

        val Empty = EventData(emptyList())

        const val ParamName = "eventData"

        fun parse(props: Any?): EventData {
            val dataProps = when (props) {
                is Map<*, *> -> {
                    props.mapNotNull {
                        if (it.key == null) return@mapNotNull null
                        Pair(it.key.toString(), it.value)
                    }
                }

                is AttributeAccessor -> {
                    props
                }

                else -> emptyList()
            }

            return EventData(dataProps)
        }
    }

    private val attributes = Attributes(props)

    override val names: Set<String> = attributes.names

    override fun get(name: String): Any? = attributes[name]

    override fun contains(name: String): Boolean = attributes.contains(name)

    override fun toString(): String = asString()
}
