package app.viewex.core.type

import app.viewex.core.util.ConvertUtils
import java.util.*
import kotlin.reflect.KClass

open class UuidType protected constructor(
    uuid: Any? = null
) : ValueType.Uuid, AbstractType<UUID>(uuid, Mapper()) {

    companion object {
        val Empty = UuidType()

        fun parseOrEmpty(uuid: Any?): UuidType = parseOrNull(uuid) ?: Empty

        fun parseOrNull(uuid: Any?): UuidType? = uuid?.let { parse(it) }

        fun parse(uuid: Any): UuidType = UuidType(uuid)
    }

    final override val rawType: KClass<UUID> = UUID::class

    private class Mapper : ValueMapper<UUID> {
        override fun mapValue(src: Any): UUID = ConvertUtils.tryOrNull {
            UUID.fromString(src.toString())
        } ?: throw IllegalArgumentException(
            "Source value [ $src ] must be as uuid [ 00000000-0000-0000-0000-000000000000 ]"
        )

    }
}
