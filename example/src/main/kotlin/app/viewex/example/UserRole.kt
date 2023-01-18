package app.viewex.example

import app.viewex.core.secutity.Principal
import app.viewex.core.type.StringType


class UserRole(
    role: String
) : Principal.Role, StringType(role, 3, 10, Principal.Role.Pattern)
