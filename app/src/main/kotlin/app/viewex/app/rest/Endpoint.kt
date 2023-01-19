package app.viewex.app.rest

import app.viewex.app.AppDefinition
import app.viewex.app.tmp.RequestParams
import app.viewex.composer.RouteQuery
import app.viewex.composer.layout.details.LayoutName
import app.viewex.composer.layout.details.LayoutType
import app.viewex.core.secutity.Principal
import app.viewex.core.type.UrlPath

abstract class Endpoint<PrincipalType : Principal<*, *>>(
    final override val method: HttpMethod,
    final override val strongPath: Boolean = true,
    name: String? = null
) : EndpointDefinition<PrincipalType> {
    object Type : LayoutType("endpoint")

    final override val layoutType: LayoutType = Type


    final override val name: LayoutName = name?.let {
        LayoutName.parse(it)
    } ?: LayoutName.nameOfClass(this::class, "Endpoint", "Layout")

    final override fun getUrl(app: AppDefinition<PrincipalType>): UrlPath = super.getUrl(app)

    final override suspend fun handle(
        principal: PrincipalType,
        requestParams: RequestParams,
        routeQuery: RouteQuery,
        additionalPath: UrlPath
    ): HttpResponse {
        if (!hasPermit(principal))
            return HttpResponse.Failed(ErrorResponse.Forbidden())
        return handleProcess(principal, requestParams, routeQuery, additionalPath)
    }

    protected abstract suspend fun handleProcess(
        principal: PrincipalType,
        requestParams: RequestParams,
        routeQuery: RouteQuery,
        additionalPath: UrlPath
    ): HttpResponse

}
