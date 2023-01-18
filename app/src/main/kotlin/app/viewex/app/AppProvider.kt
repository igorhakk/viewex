package app.viewex.app

import app.viewex.core.secutity.Principal
import java.util.*

interface AppProvider<PrincipalType : Principal<*, *>> : PageContainer<PrincipalType>, EndpointLayout<PrincipalType> {

    val endpoints: Collection<RestEndpoint<PrincipalType>>

    suspend fun getDefaultNavigateItems(locale: Locale = Locale.getDefault()): Collection<NavigateItem>

}
