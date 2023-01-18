package app.viewex.security

import app.viewex.core.secutity.Principal

interface Permission<PrincipalType : Principal<*, *>> {
    suspend fun hasPermit(principal: PrincipalType) : Boolean
}
