package app.viewex.ui

import app.viewex.composer.*
import app.viewex.composer.action.ActionName
import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.EventHandler
import app.viewex.composer.event.EventListener
import app.viewex.composer.event.EventName
import app.viewex.composer.event.MappedEventListener
import app.viewex.composer.event.mapper.RouteDataMapper
import app.viewex.composer.event.mapper.ScreenSizeDataMapper
import app.viewex.composer.layout.Layout
import app.viewex.composer.view.ResizedView
import app.viewex.core.secutity.Principal

interface UiLayout<PrincipalType : Principal<*, *>> : Layout<UiLayoutRequest> {

    companion object {
        val OnChangedRouteEventName = EventName.Basic("onChangedRoute")
    }

    val rootId: ViewId

    val context: UiContext<PrincipalType>

    val initializedContext: Boolean

    fun initContext(context: UiContext<PrincipalType>)

    fun addOnResizedUiListener(
        handler: EventHandler.Typed<ScreenSize>
    ): EventListener = MappedEventListener(
        Resizable.OnResizedUiEventName.identifyView(rootId),
        handler,
        ScreenSizeDataMapper
    ).also { context.registerListener(it) }

    fun addOnChangedRouteListener(
        handler: EventHandler.Typed<Route>
    ): EventListener = MappedEventListener(
        OnChangedRouteEventName,
        handler,
        RouteDataMapper
    ).also { context.registerListener(it) }

    fun updateRoute(
        context: ViewContext,
        route: Route
    ) = UpdateRouteAction(rootId, route).also { context.callAction(it) }

    override fun getView(request: UiLayoutRequest): ResizedView

    class UpdateRouteAction(
        viewId: ViewId,
        route: Route
    ) : ViewAction<Route>(viewId, Name, route) {
        companion object {
            val Name = ActionName("updateRoute")
        }
    }
}
