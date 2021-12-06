package tr.folksdev.kdgms.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class SigninRequest(
        @field:NotBlank
        val username : String,
        @field:NotBlank
        @field:Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "habura tutmay")
        val password : String,
) {
}