package app.viewex.composer.event


import app.viewex.composer.IdentifiedView
import app.viewex.composer.ViewId
import app.viewex.core.details.ObjectName
import app.viewex.core.type.StringType
import app.viewex.core.type.ValueType


interface EventName : ValueType.String, ObjectName {

    companion object {
        const val Pattern = "[a-z][a-zA-Z]{3,30}"

        const val ParamName = "eventName"

        fun parse(name: Any) : EventName {
            val strName = name.toString()
            if (strName.matches(Identified.Pattern.toRegex())) {
                val uuid = strName.substring(strName.length-37, strName.length-1)
                val eventName = strName.substring(0, strName.length - 38)
                return Basic(eventName).identifyView(ViewId.parse(uuid))
            }
            return Basic(strName)
        }
    }

    class Basic(
        eventName: String
    ) : EventName, StringType(eventName, 3, 30, Pattern) {

        fun identifyView(
            viewId: ViewId
        ): Identified = Identified(this, viewId)

    }

    class Identified(
        val basicEvent: Basic,
        override val viewId: ViewId
    ) : EventName, IdentifiedView, StringType(
        "${basicEvent.value}[${viewId.value}]",
        38,
        66,
        Pattern
    ) {

        companion object {
            const val Pattern = EventName.Pattern.plus("\\[[\\w\\-]{36}\\]")
        }
    }

}
