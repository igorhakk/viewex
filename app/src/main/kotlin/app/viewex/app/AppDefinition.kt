package app.viewex.app

import app.viewex.app.rest.EndpointDefinition
import app.viewex.composer.layout.details.LayoutName
import app.viewex.composer.layout.details.LayoutType
import app.viewex.core.secutity.Principal
import java.util.*

interface AppDefinition<PrincipalType : Principal<*, *>> : PageContainer<PrincipalType>, Definition<PrincipalType> {
    object Type : LayoutType("app")

    override val name: LayoutName get() = LayoutName.nameOfClass(this::class, "AppLayout", "Layout", "App")

    override val layoutType: LayoutType get() = Type

    val endpoints: Collection<EndpointDefinition<PrincipalType>>

    suspend fun getNavigateItems(locale: Locale = Locale.getDefault()): Collection<NavigateItem>

}
