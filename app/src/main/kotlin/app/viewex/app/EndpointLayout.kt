package app.viewex.app

import app.viewex.composer.layout.details.LayoutDetails
import app.viewex.composer.layout.details.LayoutName
import app.viewex.composer.layout.details.NamedLayout
import app.viewex.core.details.ObjectMetadata
import app.viewex.core.secutity.Principal
import app.viewex.security.Permission

interface EndpointLayout<PrincipalType : Principal<*, *>> : ObjectMetadata<LayoutName, LayoutDetails>, NamedLayout,
    Permission<PrincipalType> {

}
