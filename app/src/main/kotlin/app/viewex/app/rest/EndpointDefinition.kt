package app.viewex.app.rest

import app.viewex.app.AppDefinition
import app.viewex.app.Definition
import app.viewex.app.tmp.RequestParams
import app.viewex.composer.RouteQuery
import app.viewex.core.secutity.Principal
import app.viewex.core.type.UrlPath
import app.viewex.core.type.plus

interface EndpointDefinition<PrincipalType : Principal<*, *>> : Definition<PrincipalType> {

    val method: HttpMethod

    val strongPath: Boolean

    fun getUrl(app: AppDefinition<PrincipalType>): UrlPath = app.urlPath.plus(this.name)

    suspend fun handle(
        principal: PrincipalType,
        requestParams: RequestParams,
        routeQuery: RouteQuery,
        additionalPath: UrlPath
    ) : HttpResponse

}
