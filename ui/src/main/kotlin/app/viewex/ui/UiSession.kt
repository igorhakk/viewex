package app.viewex.ui

import app.viewex.composer.Route
import app.viewex.composer.ViewContext
import app.viewex.composer.ViewId
import app.viewex.composer.ViewSession
import app.viewex.core.secutity.Principal
import app.viewex.ui.message.ReceiveMessageListener
import app.viewex.ui.message.UiMessage

interface UiSession<PrincipalType : Principal<*, *>> : ViewSession {

    val rootId: ViewId

    val context: ViewContext

    val currentRoute : Route

    val principal: PrincipalType

    fun send(message: UiMessage.Direct)

    fun receive(message: UiMessage.Received)

    fun addReceiveMessageListener(listener: ReceiveMessageListener)

    fun close()

}
