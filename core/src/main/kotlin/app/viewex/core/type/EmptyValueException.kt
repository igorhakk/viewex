package app.viewex.core.type

import kotlin.reflect.KClass

class EmptyValueException(
    typeClass: KClass<*>,
    paramName: String? = null
) : Exception(
    "Class [ ${typeClass.qualifiedName} ] has an empty value"
        .plus(paramName.let { if (it == null) return@let "" else ", for param $it" })
)
