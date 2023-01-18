package app.viewex.app

import app.viewex.core.secutity.Principal
import app.viewex.core.type.UrlPath

interface PageContainer<PrincipalType : Principal<*,*>> {
    val urlPath: UrlPath
    val pages: Collection<PageLayout<PrincipalType>>
}
