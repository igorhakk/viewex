package app.viewex.ui

import app.viewex.composer.*
import app.viewex.composer.event.EventHandler
import app.viewex.composer.event.EventListener
import app.viewex.composer.event.EventName

interface UiView : Lifecycle, IdentifiedView {

    companion object {
        val OnResizedUiEventName = EventName.Basic("onResizedUi")
        val OnChangedRouteEventName = EventName.Basic("onChangedRoute")
    }

    val content: ViewTemplate?

    fun setDefaultContent(view: View)

    fun addOnResizedUiListener(handler: EventHandler.Typed<ScreenSize>): EventListener

    fun addOnChangedRouteListener(handler: EventHandler.Typed<Route>): EventListener

}
