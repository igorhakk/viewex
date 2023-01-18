package app.viewex.core.details

import java.util.*

interface DetailsProvider<Details : ObjectDetails> {
    suspend fun getDetails(locale: Locale = Locale.getDefault()): Details
}
