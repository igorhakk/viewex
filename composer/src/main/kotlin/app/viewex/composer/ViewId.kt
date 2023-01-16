package app.viewex.composer

import app.viewex.core.details.IdentifiedPath
import app.viewex.core.details.ObjectName
import app.viewex.core.util.toUuid
import java.util.*
import kotlin.reflect.KClass

class ViewId private constructor(uuid: UUID?) : ObjectName, IdentifiedPath.Item(uuid, 36) {

    companion object {

        val Empty = ViewId(null)

        const val ParamName = "viewId"

        fun generate(
            viewClass: KClass<out IdentifiedView>
        ) : ViewId = viewClass.qualifiedName?.toUuid().let {
            ViewId(it)
        }

        fun parse(uuid: Any): ViewId = ViewId(UUID.fromString(uuid.toString()))

        fun parseOrNull(uuid: Any?): ViewId? = uuid?.let { parse(it) }

    }
}
