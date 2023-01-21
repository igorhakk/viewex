package app.viewex.ui.internal

import app.viewex.composer.ManagedContent
import app.viewex.composer.View
import app.viewex.composer.ViewId
import app.viewex.composer.view.DynamicView
import app.viewex.composer.view.ResizedView
import app.viewex.core.secutity.Principal
import app.viewex.ui.UiContext
import app.viewex.ui.UiLayout
import app.viewex.ui.UiLayoutRequest

class DefaultUiLayout<PrincipalType : Principal<*, *>>(
    private val contentView: ManagedContent<View, *> = DynamicView()
) : UiLayout<PrincipalType> {


    private var _context: UiContext<PrincipalType>? = null

    private var contentLayout: ContentLayout = ContentLayout.Proxy

    private val rootView: ResizedView = ResizedView()

    override val rootId: ViewId = rootView.viewId

    override val context: UiContext<PrincipalType> get() = _context
        ?: throw IllegalStateException("Ui context has not been initialized yet")

    override val initializedContext: Boolean get() = _context != null

    override fun initContext(context: UiContext<PrincipalType>) {
        if (initializedContext)
            throw IllegalStateException("Ui context already initialized")
        _context = context
    }

    fun updateContent(view: View) {
        contentView.setContent(view).updateView(context)
    }

    override fun getView(
        request: UiLayoutRequest
    ): ResizedView = rootView.cloneWithContent(
        contentLayout.getView(
            ContentLayout.Request(contentView, request.params)
        )
    )

}
