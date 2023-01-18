package app.viewex.app

import app.viewex.app.rest.HttpMethod
import app.viewex.app.rest.HttpResponse
import app.viewex.composer.RouteQuery
import app.viewex.core.secutity.Principal
import app.viewex.core.type.UrlPath
import app.viewex.core.type.plus

interface RestEndpoint<PrincipalType : Principal<*, *>> : EndpointLayout<PrincipalType> {

    val method: HttpMethod

    val strongPath: Boolean

    fun getUrl(app: AppProvider<PrincipalType>): UrlPath = app.urlPath.plus(this.name)

    suspend fun handle(
        principal: PrincipalType,
        requestParams: RequestParams,
        routeQuery: RouteQuery,
        additionalPath: UrlPath
    ) : HttpResponse

}
