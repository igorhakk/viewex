package app.viewex.composer.layout

import app.viewex.core.attribute.Attributes

class LayoutParams(
    params: Iterable<Pair<String, Any?>>
) : Attributes(params) {
    companion object {
        val Empty = LayoutParams(emptyList())
    }
}
