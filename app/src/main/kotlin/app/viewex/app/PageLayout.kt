package app.viewex.app

import app.viewex.composer.Layout
import app.viewex.core.secutity.Principal
import app.viewex.core.type.UrlPath
import app.viewex.core.type.plus

interface PageLayout<PrincipalType : Principal<*, *>> : Layout, EndpointLayout<PrincipalType> {
    fun getUrl(pageContainer: PageContainer): UrlPath = pageContainer.urlPath.plus(this.name)
}
