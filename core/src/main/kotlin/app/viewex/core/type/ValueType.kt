package app.viewex.core.type

import app.viewex.core.util.ConvertUtils
import java.util.*
import kotlin.reflect.KClass

interface ValueType<Val : Any> {

    val rawType: KClass<Val>

    val value: Val

    val valueOrNull: Val? get() = ConvertUtils.tryOrNull { value }

    val isEmpty: kotlin.Boolean

    val isNotEmpty: kotlin.Boolean get() = !isEmpty

    object Empty : ValueType<Any> {
        override val rawType: KClass<Any> = Any::class
        override val value: Any get() = throw EmptyValueException(this::class)
        override val isEmpty: kotlin.Boolean = true
    }


    interface Boolean : ValueType<kotlin.Boolean>

    interface Number<Type : kotlin.Number> : ValueType<Type>

    interface Int : Number<kotlin.Int>

    interface Long : Number<kotlin.Long>

    interface String : ValueType<kotlin.String>

    interface Uuid : ValueType<UUID>

    interface Object<Type : Any> : ValueType<Type>

    fun ValueType<Val>.defaultEquals(other: Any?): kotlin.Boolean {
        if (this === other) return true
        if (other == null) return false
        if (!other::class.isInstance(this)) return false
        if (other is ValueType<*> && this.valueOrNull != other.valueOrNull) return false

        return true
    }

    fun ValueType<Val>.defaultHashCode(): kotlin.Int {
        return 31 * rawType.hashCode() + valueOrNull.hashCode()
    }

    fun ValueType<Val>.defaultToString(): kotlin.String = valueOrNull?.toString() ?: "empty"

}
