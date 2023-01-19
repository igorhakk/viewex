package app.viewex.app.page

import app.viewex.app.Definition
import app.viewex.app.PageContainer
import app.viewex.composer.layout.Layout
import app.viewex.composer.layout.details.LayoutName
import app.viewex.composer.layout.details.LayoutType
import app.viewex.core.secutity.Principal
import app.viewex.core.type.UrlPath
import app.viewex.core.type.plus

sealed interface PageDefinition<PrincipalType : Principal<*, *>> : Definition<PrincipalType> {

    val isGroup: Boolean

    fun getUrl(
        pageContainer: PageContainer<PrincipalType>
    ): UrlPath = pageContainer.urlPath.plus(this.name)

    interface Single<PrincipalType : Principal<*, *>>
        : PageDefinition<PrincipalType>, Layout<PageRequest> {

        object Type : LayoutType("page")

        override val name: LayoutName
            get() =
                LayoutName.nameOfClass(
                    this::class,
                    "PageLayout", "Layout", "Page"
                )

        override val layoutType: LayoutType get() = Type

        override val isGroup: Boolean get() = false


    }

    interface Group<PrincipalType : Principal<*, *>>
        : PageDefinition<PrincipalType>, PageContainer<PrincipalType> {

        object Type : LayoutType("pageGroup")

        override val name: LayoutName
            get() = LayoutName.nameOfClass(
                this::class,
                "PageGroupLayout", "PageGroup", "Layout", "Group", "Page"
            )

        override val layoutType: LayoutType get() = Type

        override val isGroup: Boolean get() = true

        override suspend fun hasPermit(
            principal: PrincipalType
        ): Boolean = allPages.any { it.hasPermit(principal) }

        fun renderPage(name: LayoutName, request: PageRequest)

        fun renderPage(name: String, request: PageRequest)
    }

}

fun <PrincipalType : Principal<*, *>> PageDefinition<PrincipalType>.asGroup() : PageDefinition.Group<PrincipalType> {
    if (this is PageDefinition.Group<PrincipalType>) return this
    throw IllegalStateException("Page definition [ ${this::class.qualifiedName} ] is not page group")
}

fun <PrincipalType : Principal<*, *>> PageDefinition<PrincipalType>.asPage() : PageDefinition.Single<PrincipalType> {
    if (this is PageDefinition.Single<PrincipalType>) return this
    throw IllegalStateException("Page definition [ ${this::class.qualifiedName} ] is not single page")
}
