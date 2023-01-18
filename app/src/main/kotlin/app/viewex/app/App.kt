package app.viewex.app

import app.viewex.composer.layout.details.LayoutDetails
import app.viewex.composer.layout.details.LayoutName
import app.viewex.core.details.DetailsProvider
import app.viewex.core.secutity.Principal
import app.viewex.core.type.UrlPath
import java.util.*

abstract class App<PrincipalType : Principal<*, *>>(
    name: String? = null
) : AppProvider<PrincipalType> {

    final override val name: LayoutName = name?.let {
        LayoutName(it)
    } ?: LayoutName.nameOfClass(this::class, "AppLayout", "Layout", "App")

    final override val urlPath: UrlPath = UrlPath(listOf(this.name))

    private val pageMap: MutableMap<LayoutName, PageLayout<PrincipalType>> = mutableMapOf()

    final override val pages: Set<PageLayout<PrincipalType>> = pageMap.values.toSet()

    private val navigatePages: MutableSet<PageLayout<PrincipalType>> = mutableSetOf()

    private val endpointMap: MutableMap<LayoutName, RestEndpoint<PrincipalType>> = mutableMapOf()

    final override val endpoints: Set<RestEndpoint<PrincipalType>> get() = endpointMap.values.toSet()


    protected fun <Page : PageLayout<PrincipalType>> registerPage(
        page: Page
    ): Page {
        if (pageMap.containsKey(page.name))
            throw IllegalStateException("Page with name [ ${page.name} ] already exists")

        pageMap[page.name] = page

        return page
    }

    private fun <Page : PageLayout<PrincipalType>> bindNavigatePage(
        page: Page
    ): Page {
        if (!pageMap.containsValue(page))
            throw IllegalStateException(
                "Page [ name: ${page.name}, class: ${page::class.qualifiedName} ] not registered yet"
            )

        navigatePages.add(page)

        return page
    }

    protected fun <Endpoint : RestEndpoint<PrincipalType>> registerEndpoint(
        endpoint: Endpoint
    ): Endpoint {
        if (endpointMap.containsKey(endpoint.name))
            throw IllegalStateException("Endpoint with name [ ${endpoint.name} ] already exists")

        endpointMap[endpoint.name] = endpoint

        return endpoint
    }

    final override suspend fun getDefaultNavigateItems(
        locale: Locale
    ): Collection<NavigateItem> = pageMap.values.map {
        val details = it.getDetails(locale)
        NavigateItem(
            it.name,
            details.label,
            details.description,
            details.icon,
            it.getUrl(this),
            navigatePages.indexOf(it) * 100
        )
    }

    override suspend fun hasPermit(
        principal: PrincipalType
    ): Boolean = pages.any { it.hasPermit(principal) }

    protected fun <Page : PageLayout<PrincipalType>> Page.navigateItem(): Page {
        this@App.bindNavigatePage(this)
        return this
    }

    final override suspend fun getDetails(locale: Locale): LayoutDetails = detailsProvider.getDetails(locale)

    protected abstract val detailsProvider: DetailsProvider<LayoutDetails>

}
