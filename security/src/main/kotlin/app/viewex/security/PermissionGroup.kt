package app.viewex.security

import app.viewex.core.details.ObjectGroup

open class PermissionGroup(items: Iterable<Item>) : ObjectGroup(items)

fun PermissionGroup.pathForType(type: PermissionType) : PermissionPath = PermissionPath(this, type)
