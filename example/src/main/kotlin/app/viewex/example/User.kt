package app.viewex.example

import app.viewex.core.secutity.Principal
import app.viewex.core.type.UuidType

class User(
    override val id: Id,
    override val role: UserRole
) : Principal<User.Id, UserRole> {

    class Id(
        uuid: Any
    ) : Principal.Id, UuidType(uuid)
}
