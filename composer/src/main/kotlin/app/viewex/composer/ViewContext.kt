package app.viewex.composer

import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.EventListener

interface ViewContext {

    val session: SessionInfo

    val contentHolder: ContentHolder

    fun attached(viewId: ViewId): Boolean

    fun registerListener(listener: EventListener)

    fun callAction(action: ViewAction<*>)

}
