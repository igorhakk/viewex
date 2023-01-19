package app.viewex.composer.layout.details

import app.viewex.core.details.ObjectName
import app.viewex.core.exception.AnonymousClassException
import app.viewex.core.type.UrlPath
import app.viewex.core.util.firstToLower
import kotlin.reflect.KClass

class LayoutName private constructor(
    name: String?
) : ObjectName, UrlPath.Item(name) {

    companion object {
        val Empty = LayoutName(null)

        fun parse(name: String): LayoutName = LayoutName(name)

        fun nameOfClass(
            layoutClass: KClass<out NamedLayout>,
            vararg removeSuffix: String
        ): LayoutName = layoutClass.simpleName?.let {
            var name = it
            removeSuffix.toList().forEach { suffix ->
                name = name.removeSuffix(suffix)
            }
            LayoutName(name.firstToLower())
        } ?: throw AnonymousClassException(NamedLayout::class)

    }

}
