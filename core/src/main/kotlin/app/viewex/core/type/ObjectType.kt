package app.viewex.core.type

import kotlin.reflect.KClass

open class ObjectType<Type : Any>(
    private val obj: Type?,
    objClass: KClass<Type>,
) : ValueType.Object<Type>{

    final override val rawType: KClass<Type> = objClass

    final override val value: Type get() = obj ?: throw EmptyValueException(this::class)

    final override val isEmpty: Boolean = obj != null

}
