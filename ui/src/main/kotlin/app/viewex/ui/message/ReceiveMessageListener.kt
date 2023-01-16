package app.viewex.ui.message

interface ReceiveMessageListener {

    val messageType: UiMessage.Type

    fun handle(data: UiMessage.DataParams)

}
