package app.viewex.composer.event

import app.viewex.core.util.ClassUtils

open class BasicEventListener(
    override val name: EventName,
    private val handler: EventHandler
) : EventListener {

    constructor(
        name: EventName,
        handler: EventHandler.Basic
    ) : this(name, EventHandler { handler.handle() })

    override fun handle(data: EventData) = handler.handle(data)

    override fun toString(): String = ClassUtils.memberPropsToString(this)
}
