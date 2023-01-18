package app.viewex.security

import app.viewex.core.details.IdentifiedPath

class PermissionPath(
    group: PermissionGroup,
    override val name: PermissionType
) : IdentifiedPath.Named<PermissionType>(group.items.plus(name))
