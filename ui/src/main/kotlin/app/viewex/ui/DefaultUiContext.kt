package app.viewex.ui

import app.viewex.composer.View
import app.viewex.composer.ViewPropSet
import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.EventHandler
import app.viewex.composer.event.EventListener
import app.viewex.composer.theme.Theme

class DefaultUiContext(
    override val theme: Theme,
    uiViewFactory: UiViewFactory
) : UiContext {

    private val listenerSet: ListenerSet = ListenerSet()

    override val uiView = uiViewFactory.create(this)

    override val attached: Boolean get() = uiView.attached

    private var _session: UiSession? = null

    override val session: UiSession
        get() = _session
            ?: throw IllegalStateException(
                "Session for Ui [ ${uiView.viewId}] not initialized yet"
            )

    private val actionPublisher = ActionPublisher(this)

    override fun registerListener(listener: EventListener) = listenerSet.add(listener)

    override fun initSession(session: UiSession) {
        if (_session != null)
            throw IllegalStateException(
                "Session for Ui [ ${uiView.viewId}] initialized already"
            )
        _session = session
    }

    override fun setUiDefaultContent(view: View) = uiView.setDefaultContent(view)

    override fun renderUiContent(view: View) = uiView.renderContent(view)

    override fun callAction(action: ViewAction<*>) = actionPublisher.publish(action)

    override fun addOnAttachedListener(
        handler: EventHandler.Basic
    ): EventListener = uiView.addOnAttachedListener(handler)

    override fun addOnDetachedListener(
        handler: EventHandler.Basic
    ): EventListener = uiView.addOnDetachedListener(handler)

    override fun addOnUpdatedContentListener(
        handler: EventHandler.Basic
    ): EventListener = uiView.addOnUpdatedContentListener(handler)

    override fun addOnUpdatedPropListener(
        handler: EventHandler.Typed<ViewPropSet.Prop>
    ): EventListener = uiView.addOnUpdatedPropListener(handler)

}
