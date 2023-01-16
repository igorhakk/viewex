package app.viewex.ui.message

import app.viewex.core.attribute.AttributeAccessor
import app.viewex.core.attribute.Attributes
import app.viewex.core.type.StringType
import app.viewex.core.util.ConvertUtils
import com.sun.nio.sctp.IllegalReceiveException

sealed interface UiMessage<Data> {
    val type: Type
    val data: Data

    class Received(
        override val type: Type,
        override val data: DataParams = DataParams.Empty
    ) : UiMessage<DataParams> {

        companion object {
            val Ping = Received(Type.Ping)

            val Pong = Received(Type.Pong)

            fun parse(message: Map<*, *>): Received {

                val type = message["type"]?.toString()?.let {
                    Type(it)
                } ?: throw IllegalReceiveException(
                    "Type [ value: ${message["type"]} ] is not recognized for the received message"
                )

                val params = ConvertUtils.toMap(message["type"])?.mapNotNull {
                    if (it.key == null) return@mapNotNull null
                    Pair(it.key.toString(), it.value)
                } ?: emptyList()

                return Received(type, DataParams(params))
            }
        }
    }

    open class Direct(
        override val type: Type,
        override val data: Any
    ) : UiMessage<Any> {
        companion object {
            val Ping = Direct(Type.Ping, Unit)

            val Pong = Direct(Type.Pong, Unit)
        }
    }


    open class Type(
        name: String
    ) : StringType(name, 3, 50, Pattern) {
        companion object {
            const val Pattern = "[a-z][A-Za-z]*"

            val Ping = Type("ping")

            val Pong = Type("pong")

        }
    }

    class DataParams(
        params: Iterable<Pair<String, Any?>>
    ) : AttributeAccessor {

        companion object {
            val Empty = DataParams(emptyList())
        }
        private val attributes = Attributes(params)

        override val names: Set<String> = attributes.names

        override fun get(name: String): Any? = attributes[name]

        override fun contains(name: String): Boolean = attributes.contains(name)

    }
}
