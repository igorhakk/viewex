package app.viewex.app.localization

import app.viewex.core.attribute.PairList

interface MessageSource {

    fun getMessage(
        source: MessagePath,
        params: PairList.() -> Unit
    ): String? = getMessage(source, MessageParams(params))

    fun getMessage(
        source: MessagePath,
        params: MessageParams = MessageParams.Empty
    ): String?
}
