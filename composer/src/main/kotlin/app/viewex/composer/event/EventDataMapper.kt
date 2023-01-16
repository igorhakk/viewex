package app.viewex.composer.event

fun interface EventDataMapper<Res> {
    fun map(data: EventData): Res
}
