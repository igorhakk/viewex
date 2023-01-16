package app.viewex.core.util

import app.viewex.core.type.ValueType
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.staticFunctions

object ConvertUtils {

    fun toNumber(source: Any?): Number? {
        val value = if (source is ValueType<*>) {
            source.valueOrNull
        } else {
            source
        }
        return when (value) {
            is Number -> value
            is String -> value.replace("[^0-9]*".toRegex(), "").toBigDecimalOrNull()
            else -> null
        }
    }

    fun toMap(source: Any?): Map<*, *>? {
        return when (source) {
            is Map<*, *> -> source
            else -> null
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Enum<*>> toEnum(source: Any?, type: KClass<T>): T? {
        return tryOrNull {
            val a = type.staticFunctions.first { it.name == "valueOf" }
            a.call(source) as T
        }
    }

    inline fun <T> tryOrNull(invoke: () -> T): T? {
        return try {
            invoke()
        } catch (_: Exception) {
            null
        }
    }

}

fun Any?.toNumber(): Number? = ConvertUtils.toNumber(this)

fun Any?.toMap(): Map<*, *>? = ConvertUtils.toMap(this)

fun CharSequence.toUuid(): UUID {
    val mostSigBits = this.hashCode().toLong()
    val leastSigBits = System.currentTimeMillis()
    return UUID(mostSigBits,leastSigBits)
}
