package app.viewex.composer.view


import app.viewex.core.type.ValueType
import app.viewex.core.util.StringUtils
import kotlin.reflect.KClass

interface EnumProp : ValueType.String {

    val name: String

    override val rawType: KClass<String> get() = String::class

    override val value: String get() = StringUtils.camelCaseToString(name,"-")

    override val isEmpty: Boolean get() = false

}
