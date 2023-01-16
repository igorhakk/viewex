package app.viewex.app.localization


import java.util.*

interface TranslateService {

    suspend fun contains(
        path: MessagePath,
        locale: Locale = Locale.getDefault()
    ) : Boolean

    suspend fun allSources(
        locale: Locale = Locale.getDefault()
    ) : MessageSource = getSource(emptyList(), locale)

    suspend fun getSource(
        path: MessagePath,
        locale: Locale = Locale.getDefault()
    ) : MessageSource = getSource(listOf(path), locale)

    suspend fun getSource(
        groups: Iterable<MessagePath>,
        locale: Locale = Locale.getDefault()
    ) : MessageSource

}
