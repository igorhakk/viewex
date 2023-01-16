package app.viewex.core.type

abstract class AbstractType<Val : Any>(
    value: Any?,
    validator: ValueMapper<Val>
) : ValueType<Val> {

    private val validValue = let {
        value ?: return@let null
        validator.mapValue(value)
    }

    final override val valueOrNull: Val? = validValue

    final override val value: Val
        get() = validValue ?: throw EmptyValueException(this::class)

    final override val isEmpty: kotlin.Boolean get() = validValue == null

    final override val isNotEmpty: kotlin.Boolean get() = !isEmpty

    override fun toString(): kotlin.String = defaultToString()

    override fun equals(other: Any?): kotlin.Boolean = defaultEquals(other)

    override fun hashCode(): kotlin.Int = defaultHashCode()

}
