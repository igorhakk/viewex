package app.viewex.core.secutity

interface Principal<ID : Principal.Id, Role : Principal.Role> {

    val id: ID

    val role: Role

    interface Id

    interface Role {

        companion object {
            const val Pattern = "[A-Z\\_]{3,10}"
        }

    }
}
