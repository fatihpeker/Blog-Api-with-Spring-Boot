package tr.folksdev.kdgms.dto

import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UpdateUserRequest(
        @field:Size(min = 3)
        val username : String? = null,
        @field:Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "dismatch with password pattern")
        val password : String? = null,
        val name : String? = null,
        val surname : String? = null
) {
}