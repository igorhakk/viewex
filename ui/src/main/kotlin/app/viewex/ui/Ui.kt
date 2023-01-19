package app.viewex.ui

import app.viewex.app.AppDefinition
import app.viewex.composer.ViewId
import app.viewex.composer.layout.details.LayoutDetails
import app.viewex.composer.layout.details.LayoutName
import app.viewex.core.details.DetailsDefinition
import app.viewex.core.secutity.Principal
import java.util.*

abstract class Ui<PrincipalType : Principal<*, *>>(
    uiName: String? = null
) : UiProvider<PrincipalType> {

    private val uiContextMap: MutableMap<ViewId, UiContext<PrincipalType>> = mutableMapOf()

    private val sessionSet = SessionSet()

    final override val activeSessions: Set<UiSession<PrincipalType>> get() = sessionSet.allSessions

    override val name: LayoutName = uiName?.let {
        LayoutName(it)
    } ?: LayoutName.nameOfClass(this::class, "UiLayout", "Layout", "Ui")

    private val appMap: MutableMap<String, AppDefinition<PrincipalType>> = mutableMapOf()

    final override val apps: Collection<AppDefinition<PrincipalType>> get() = appMap.values

    protected fun <App : AppDefinition<PrincipalType>> registerApp(app: App): App {
        if (appMap.containsKey(app.name.value))
            throw IllegalStateException("App with name: [ ${app.name} ] already exists")

        appMap[app.name.value] = app

        return app
    }
    final override fun getSession(rootId: ViewId): UiSession<PrincipalType> = sessionSet[rootId]

    final override fun containsSession(rootId: ViewId): Boolean = sessionSet.contains(rootId)

    final override fun initUiView(): UiView = createContext().also {
        uiContextMap[it.session.rootId] = it
    }.uiView

    final override fun startSession(session: UiSession<PrincipalType>) = uiContextMap[session.rootId]?.initSession(session)
            ?: throw IllegalStateException("Session [ ${session.rootId} ] not initialized")

    final override fun closeSession(rootId: ViewId) {
        sessionSet.remove(rootId)
        uiContextMap.remove(rootId)
    }

    final override suspend fun getDetails(locale: Locale): LayoutDetails = detailsProvider.getDetails(locale)

    protected abstract val detailsProvider: DetailsDefinition<LayoutDetails>

    protected abstract fun createContext(): UiContext<PrincipalType>
}
