package app.viewex.core.details

import java.util.*

interface DetailsDefinition<Details : ObjectDetails> {
    suspend fun getDetails(locale: Locale = Locale.getDefault()): Details
}
