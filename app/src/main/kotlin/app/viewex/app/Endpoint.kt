package app.viewex.app

import app.viewex.app.rest.ErrorResponse
import app.viewex.app.rest.HttpMethod
import app.viewex.app.rest.HttpResponse
import app.viewex.composer.RouteQuery
import app.viewex.composer.layout.details.LayoutName
import app.viewex.composer.layout.details.LocalizedDetails
import app.viewex.core.secutity.Principal
import app.viewex.core.type.UrlPath

abstract class Endpoint<PrincipalType : Principal<*, *>>(
    final override val method: HttpMethod,
    final override val strongPath: Boolean = true,
    name: String? = null
) : RestEndpoint<PrincipalType> {

    final override val name: LayoutName = name?.let {
        LayoutName(it)
    } ?: LayoutName.nameOfClass(this::class, "Endpoint", "Layout")

    private val details = LocalizedDetails(
        MessageGroup(MessageGroup.Type.Endpoint, this.name)
    )

    final override fun getUrl(app: AppProvider<PrincipalType>): UrlPath = super.getUrl(app)

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
