package app.viewex.composer

import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.EventListener
import app.viewex.composer.theme.Theme

interface ViewContext {

    val session: ViewSession

    val theme: Theme

    fun registerListener(listener: EventListener)

    fun callAction(action: ViewAction<*>)

}
