package app.viewex.ui.internal

import app.viewex.composer.ContentHolder
import app.viewex.composer.ViewId
import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.EventListener
import app.viewex.core.secutity.Principal
import app.viewex.ui.ListenerSet
import app.viewex.ui.UiContext
import app.viewex.ui.UiSession

class DefaultUiContext<PrincipalType : Principal<*, *>>(
    override val session: UiSession<PrincipalType>,
    override val contentHolder: ContentHolder = DefaultContentHolder()
) : UiContext<PrincipalType> {

    private var _session: UiSession<PrincipalType>? = null

    private val actionPublisher = ActionPublisher()

    private val listenerSet = ListenerSet()

    override fun attached(viewId: ViewId): Boolean = actionPublisher.attached(viewId)

    override fun registerListener(
        listener: EventListener
    ) = listenerSet.add(listener)

    override fun callAction(
        action: ViewAction<*>
    ) = actionPublisher.publish(action)

}
