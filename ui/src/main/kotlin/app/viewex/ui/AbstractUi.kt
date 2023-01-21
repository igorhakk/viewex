package app.viewex.ui

import app.viewex.app.localization.TranslateService
import app.viewex.app.tmp.AppLayout
import app.viewex.composer.Route
import app.viewex.composer.View
import app.viewex.composer.ViewId
import app.viewex.composer.layout.details.AbstractDetailedLayout
import app.viewex.core.secutity.Principal

abstract class AbstractUi<PrincipalType : Principal<*, *>>(
    translateService: TranslateService,
    uiName: String? = null
) : UiProvider<PrincipalType>, AbstractDetailedLayout<PrincipalType>(
    UiProvider.LayoutType,
    translateService,
    listOf("UiLayout", "Layout", "Ui"),
    uiName
) {

    private val layoutMap = UiLayoutMap<PrincipalType>()

    final override val activeSessions: Set<UiSession<PrincipalType>>
        get() = layoutMap.allLayouts.map {
            it.context.session
        }.toSet()

    override val apps: Collection<AppLayout<PrincipalType>>
        get() = TODO("Not yet implemented")

    final override fun getContext(
        rootId: ViewId
    ): UiContext<PrincipalType> = layoutMap[rootId].context

    final override fun initializedContext(
        rootId: ViewId
    ): Boolean = layoutMap.getOrNull(rootId)?.initializedContext ?: false

    final override fun initUiLayout(route: Route): View = createLayout().let {
        layoutMap.add(it)
        it.rootView
    }

    override fun startSession(session: UiSession<PrincipalType>) {
        layoutMap[session.rootId].initContext(createContext(session))
    }

    override fun closeSession(rootId: ViewId) {
        layoutMap.remove(rootId)
    }

    protected abstract fun createContext(session: UiSession<PrincipalType>) : UiContext<PrincipalType>

    protected abstract fun createLayout(): UiLayout<PrincipalType>
}
