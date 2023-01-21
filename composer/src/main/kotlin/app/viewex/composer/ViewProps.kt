package app.viewex.composer

import app.viewex.core.attribute.Attributes

interface ViewProps {
    object Empty : ViewProps

    class Mutable : Attributes.Mutable(), ViewProps
}
