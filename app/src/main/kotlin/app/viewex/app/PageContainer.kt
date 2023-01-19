package app.viewex.app

import app.viewex.app.page.PageDefinition
import app.viewex.composer.layout.details.LayoutName
import app.viewex.core.secutity.Principal
import app.viewex.core.type.UrlPath

interface PageContainer<PrincipalType : Principal<*, *>> {

    val urlPath: UrlPath

    val allPages: Collection<PageDefinition<PrincipalType>>

    fun get(name: String): PageDefinition<PrincipalType>

    fun get(name: LayoutName): PageDefinition<PrincipalType>

    fun getOrNull(name: LayoutName): PageDefinition<PrincipalType>?

    fun getOrNull(name: String): PageDefinition<PrincipalType>?

}
