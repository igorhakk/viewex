package app.viewex.ui

import app.viewex.composer.*
import app.viewex.composer.event.EventHandler
import app.viewex.composer.event.EventListener
import app.viewex.composer.event.MappedEventListener
import app.viewex.composer.event.mapper.RouteDataMapper
import app.viewex.composer.event.mapper.ScreenSizeDataMapper

class DefaultUiView(
    context: UiContext<*>
) : UiView, ManagedView(context) {

    private var _content: View? = null

    override val content: ViewTemplate? get() = _content?.getTemplate()

    override fun setDefaultContent(view: View) {
        if (attached)
            throw IllegalStateException("Ui has been initialized and attached")
        content
    }



    override fun addOnResizedUiListener(
        handler: EventHandler.Typed<ScreenSize>
    ): EventListener = MappedEventListener(
        UiView.OnResizedUiEventName,
        handler,
        ScreenSizeDataMapper
    )

    override fun addOnChangedRouteListener(
        handler: EventHandler.Typed<Route>
    ): EventListener = MappedEventListener(
        UiView.OnChangedRouteEventName,
        handler,
        RouteDataMapper
    )

}
