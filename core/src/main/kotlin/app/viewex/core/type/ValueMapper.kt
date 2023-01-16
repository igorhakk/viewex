package app.viewex.core.type

fun interface ValueMapper<Val : Any> {

    @Throws(IllegalArgumentException::class)
    fun mapValue(src: Any): Val

    class Empty<Val : Any> : ValueMapper<Val> {
        override fun mapValue(
            src: Any
        ): Val = throw UnsupportedOperationException("Empty mapper cannot map a value")
    }
}
