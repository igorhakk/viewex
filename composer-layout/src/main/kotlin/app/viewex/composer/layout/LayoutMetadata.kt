package app.viewex.composer.layout

import app.viewex.core.details.ObjectMetadata

interface LayoutMetadata : ObjectMetadata<LayoutName, LayoutDetails> {

    interface Routed : LayoutMetadata {
        override val details: LayoutDetails.Route
    }
}
