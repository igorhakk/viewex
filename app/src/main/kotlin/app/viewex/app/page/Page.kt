package app.viewex.app.page

import app.viewex.app.PageContainer
import app.viewex.composer.layout.details.LayoutName
import app.viewex.composer.layout.details.LayoutType
import app.viewex.core.secutity.Principal
import app.viewex.core.type.UrlPath

abstract class Page<PrincipalType : Principal<*, *>>(
    name: String? = null
) : PageDefinition.Single<PrincipalType> {

    final override val name: LayoutName = name?.let {
        LayoutName.parse(it)
    } ?: super.name

    final override val layoutType: LayoutType = super.layoutType

    final override val isGroup: Boolean = super.isGroup

    final override fun getUrl(
        pageContainer: PageContainer<PrincipalType>
    ): UrlPath = super.getUrl(pageContainer)


}
