package app.viewex.composer.event

fun interface EventHandler {

    fun handle(data: EventData)

    fun interface Basic {
        fun handle()
    }

    fun interface Typed<T> {
        fun handle(data: T)
    }
}
