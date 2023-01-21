package app.viewex.composer.layout.details

import app.viewex.composer.layout.NamedLayout
import app.viewex.core.details.ObjectMetadata
import app.viewex.core.secutity.Principal

interface DetailedLayout<PrincipalType : Principal<*, *>>
    : ObjectMetadata<LayoutName, LayoutDetails>, NamedLayout {

    val layoutType: LayoutType

}
