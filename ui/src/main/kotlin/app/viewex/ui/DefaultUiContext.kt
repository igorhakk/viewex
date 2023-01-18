package app.viewex.ui

import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.EventListener
import app.viewex.composer.theme.Theme
import app.viewex.core.secutity.Principal

class DefaultUiContext<PrincipalType : Principal<*, *>>(
    override val theme: Theme
) : UiContext<PrincipalType> {

    private val listenerSet: ListenerSet = ListenerSet()

    override val uiView = DefaultUiView(this)

    private var _session: UiSession<PrincipalType>? = null

    override val session: UiSession<PrincipalType>
        get() = _session
            ?: throw IllegalStateException(
                "Session for the ui view [ ${uiView.viewId}] not initialized yet"
            )

    private val actionPublisher = ActionPublisher(this)

    override fun registerListener(listener: EventListener) = listenerSet.add(listener)

    override fun initSession(session: UiSession<PrincipalType>) {
        if (_session != null)
            throw IllegalStateException(
                "Session for the ui view [ ${uiView.viewId}] initialized already"
            )
        _session = session
    }

    override fun callAction(action: ViewAction<*>) = actionPublisher.publish(action)

}
