package app.viewex.composer.layout.details

import app.viewex.core.details.IdentifiedPath
import app.viewex.localization.MessagePath

class MessageGroup(
    val type: Type,
    val layoutName: LayoutName
) {

    val groupPath: MessagePath = MessagePath(type.value, layoutName.value)

    class Type(type: String) : IdentifiedPath.Item(type) {
        companion object {
            val Ui = Type("ui")
            val App = Type("app")
            val Page = Type("page")
            val Endpoint = Type("endpoint")
        }
    }
}

fun MessageGroup.getMessagePath(
    sourceItem: IdentifiedPath.Item
) : MessagePath = MessagePath(groupPath.items.plus(sourceItem))
