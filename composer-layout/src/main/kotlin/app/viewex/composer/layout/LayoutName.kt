package app.viewex.composer.layout

import app.viewex.composer.layout.page.PageLayout
import app.viewex.core.details.ObjectName
import app.viewex.core.exception.AnonymousClassException
import app.viewex.core.type.UrlPath
import app.viewex.core.util.firstToLower
import kotlin.reflect.KClass

class LayoutName(name: String) : ObjectName, UrlPath.Item(name) {

    companion object {

        fun nameOfClass(
            layoutClass: KClass<out LayoutMetadata>,
            vararg removeSuffix: String
        ): LayoutName = layoutClass.simpleName?.let {
            var name = it
            removeSuffix.toList().forEach { suffix ->
                name = name.removeSuffix(suffix)
            }
            LayoutName(name.firstToLower())
        } ?: throw AnonymousClassException(PageLayout::class)

    }

}
