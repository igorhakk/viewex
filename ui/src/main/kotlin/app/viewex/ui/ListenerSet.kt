package app.viewex.ui

import app.viewex.composer.View
import app.viewex.composer.event.EventListener
import kotlin.reflect.KClass

class ListenerSet(
    private val viewClass: KClass<out View>? = null
) : Set<EventListener> {

    private val listeners: MutableSet<EventListener> = mutableSetOf()

    fun add(listener: EventListener) {
        if (listeners.contains(listener))
            throw IllegalStateException(
                "Listener [ name: ${listener.name}, " +
                        "class: ${listener::class.qualifiedName} ] already exist" +
                        let {
                            if (viewClass == null) return@let ""
                            ", view [ class: ${viewClass.qualifiedName} ]"
                        }
            )
        listeners.add(listener)
    }

    fun remove(listener: EventListener) {
        if (!listeners.contains(listener))
            throw IllegalStateException(
                "Listener [ name: ${listener.name}, " +
                        "class: ${listener::class.qualifiedName} ] not found" +
                        let {
                            if (viewClass == null) return@let ""
                            ", view [ class: ${viewClass.qualifiedName} ]"
                        }
            )
        listeners.remove(listener)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun contains(listener: EventListener): Boolean = listeners.contains(listener)

    override val size: Int = listeners.size

    override fun isEmpty(): Boolean = listeners.isEmpty()

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun containsAll(
        listeners: Collection<EventListener>
    ): Boolean = this.listeners.containsAll(listeners)

    override fun iterator(): Iterator<EventListener> = listeners.toList().iterator()

}
