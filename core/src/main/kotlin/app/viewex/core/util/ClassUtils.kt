package app.viewex.core.util

import app.viewex.core.exception.AnonymousClassException
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

object ClassUtils {

    fun memberPropsToString(obj: Any, vararg excludePropName: String): String {

        val props = obj::class.declaredMemberProperties.mapNotNull { prop ->
            if (prop.visibility != KVisibility.PUBLIC || excludePropName.toList().any { prop.name == it })
                return@mapNotNull null
            "${prop.name}=${prop.getter.call(obj)}"
        }.joinToString(", ")

        return obj::class.simpleName?.let {
            "${obj::class.simpleName} { $props }"
        } ?: throw AnonymousClassException(obj::class)
    }
}

fun KClass<*>.composeName(divider: String = " "): String {
    val name = this.simpleName ?: throw AnonymousClassException(this)
    return StringUtils.camelCaseToString(name, divider)
}
