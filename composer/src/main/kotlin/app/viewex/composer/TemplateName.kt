package app.viewex.composer

import app.viewex.core.exception.AnonymousClassException
import app.viewex.core.type.StringType
import kotlin.reflect.KClass

class TemplateName(
    name: String
) : StringType(name, 2, 30, Pattern) {

    companion object {

        const val Pattern: String = "[A-Z][A-Za-z]{1,29}"

        val Blank = TemplateName("BlankView")

        fun ofDefinitionClass(
            definitionClass: KClass<out View>
        ): TemplateName = definitionClass.simpleName?.let { className ->
            if (className.endsWith("View"))
                return@let TemplateName(className)
            return@let TemplateName(className + "View")
        } ?: throw AnonymousClassException(View::class)
    }

}
