package app.viewex.composer.event

import app.viewex.core.details.Named

interface EventListener : Named<EventName>, EventHandler {
    interface IdentifiedView : EventListener {
        override val name: EventName.Identified
    }
}
