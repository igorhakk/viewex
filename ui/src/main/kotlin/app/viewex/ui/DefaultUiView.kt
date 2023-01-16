package app.viewex.ui

import app.viewex.composer.ManagedView
import app.viewex.composer.Route
import app.viewex.composer.ScreenSize
import app.viewex.composer.View
import app.viewex.composer.event.EventHandler
import app.viewex.composer.event.EventListener
import app.viewex.composer.event.MappedEventListener
import app.viewex.composer.event.mapper.RouteDataMapper
import app.viewex.composer.event.mapper.ScreenSizeDataMapper
import app.viewex.composer.layout.LayoutDetails
import app.viewex.composer.layout.LayoutName
import java.util.function.Supplier

class DefaultUiView(
    context: UiContext,
    private val detailsSupplier: Supplier<LayoutDetails>
) : UiView, ManagedView(context) {

    private var content: View? = null

    override val name: LayoutName = LayoutName("ui")

    override val details: LayoutDetails get() = detailsSupplier.get()

    override val template: UiTemplate get() = UiTemplate(viewId, content?.getTemplate())

    override fun setDefaultContent(view: View) {
        if (attached)
            throw IllegalStateException("Ui has been initialized and attached")
        content
    }

    override fun renderContent(view: View) = updateContent(view.getTemplate())

    override fun addOnResizedUiListener(
        handler: EventHandler.Typed<ScreenSize>
    ): EventListener = MappedEventListener(UiView.OnResizedUiEventName, handler, ScreenSizeDataMapper)

    override fun addOnChangedRouteListener(
        handler: EventHandler.Typed<Route>
    ): EventListener = MappedEventListener(UiView.OnChangedRouteEventName, handler, RouteDataMapper)

}
