package app.viewex.ui

import app.viewex.app.tmp.NavigateItem
import app.viewex.composer.Resizable
import app.viewex.composer.Route
import app.viewex.composer.ScreenSize
import app.viewex.composer.ViewId
import app.viewex.composer.action.ActionName
import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.EventHandler
import app.viewex.composer.event.EventListener
import app.viewex.composer.event.EventName
import app.viewex.composer.event.MappedEventListener
import app.viewex.composer.event.mapper.RouteDataMapper
import app.viewex.composer.event.mapper.ScreenSizeDataMapper
import app.viewex.composer.layout.Layout
import app.viewex.composer.layout.LayoutParams
import app.viewex.composer.layout.LayoutRequest
import app.viewex.composer.view.ResizedView
import app.viewex.core.secutity.Principal
import app.viewex.core.type.UrlPath

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
        route: Route
    ) = UpdateRouteAction(rootId, route).also { context.callAction(it) }

    override fun getView(request: Request): ResizedView

    class UpdateRouteAction(
        viewId: ViewId,
        route: Route
    ) : ViewAction<Route>(viewId, Name, route) {
        companion object {
            val Name = ActionName("updateRoute")
        }
    }

    class Request(
        val urlPath: UrlPath,
        val navigateItems: Iterable<NavigateItem>,
        override val params: LayoutParams
    ) : LayoutRequest {

        companion object {
            fun of(
                route: Route,
                navigateItems: Iterable<NavigateItem>
            ): Request = Request(route.path, navigateItems, LayoutParams(route.query))
        }

    }
}
