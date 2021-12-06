package tr.folksdev.kdgms.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class RegisterRequest(

        @field:NotBlank
        @field:Size(min = 3)
        val username : String,
        @field:NotBlank
        @field:Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "habura tutmay")
        val password : String,

        val name : String,
        val surname : String

) {
}