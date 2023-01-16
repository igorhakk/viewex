package app.viewex.core.exception

import kotlin.reflect.KClass

class AnonymousClassException(
    supertype: KClass<*>,
    additionalMessage: String? = null
) : Exception(
    "Class [ ${supertype.qualifiedName} ] can't be an anonymous instance".let {
        if (additionalMessage != null) {
            return@let it.plus(" [ $additionalMessage ]")
        }
        it
    }
)
