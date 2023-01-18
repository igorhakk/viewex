package app.viewex.composer.event.mapper

import app.viewex.composer.RouteQuery
import app.viewex.composer.Route
import app.viewex.composer.event.EventData
import app.viewex.composer.event.EventDataMapper
import app.viewex.core.type.EmptyValueException
import app.viewex.core.type.UrlPath

object RouteDataMapper : EventDataMapper<Route> {

    override fun map(data: EventData): Route {

        val path = data[Route.PathParam]?.toString()?.let {
            UrlPath.parse(it)
        } ?: throw EmptyValueException(UrlPath::class, Route.QueryParam)

        val query = data[Route.QueryParam]?.let { query ->
            if (query !is Map<*,*>) throw IllegalArgumentException("Route query value must be Map<String, Any?>")
            RouteQuery(query.mapNotNull {
                if (it.key == null) return@mapNotNull null
                Pair(it.key.toString(), it.value)
            })
        } ?: RouteQuery.Empty

        return Route(path, query)
    }

}
