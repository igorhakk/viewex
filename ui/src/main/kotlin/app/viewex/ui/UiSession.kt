package app.viewex.ui

import app.viewex.composer.Route
import app.viewex.composer.SessionInfo
import app.viewex.composer.ViewId
import app.viewex.core.secutity.Principal
import app.viewex.ui.message.ReceiveMessageListener
import app.viewex.ui.message.UiMessage

interface UiSession<PrincipalType : Principal<*, *>> : SessionInfo {

    val rootId: ViewId

    val currentRoute : Route

    val principal: PrincipalType

    fun send(message: UiMessage.Direct)

    fun receive(message: UiMessage.Received)

    fun addReceiveMessageListener(listener: ReceiveMessageListener)

    fun close()

}
