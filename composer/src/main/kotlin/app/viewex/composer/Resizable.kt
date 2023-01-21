package app.viewex.composer

import app.viewex.composer.event.EventHandler
import app.viewex.composer.event.EventListener
import app.viewex.composer.event.EventName
import app.viewex.composer.event.MappedEventListener
import app.viewex.composer.event.mapper.ScreenSizeDataMapper

interface Resizable : IdentifiedView {

    companion object {
        val OnResizedUiEventName = EventName.Basic("onResizedUi")
    }

    fun addOnResizedUiListener(
        context: ViewContext,
        handler: EventHandler.Typed<ScreenSize>
    ): EventListener = MappedEventListener(
        OnResizedUiEventName.identifyView(viewId),
        handler,
        ScreenSizeDataMapper
    ).also { context.registerListener(it) }
}
