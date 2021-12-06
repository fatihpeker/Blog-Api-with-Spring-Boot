package tr.folksdev.kdgms.dto

import tr.folksdev.kdgms.model.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreatePostRequest(
        @field:NotBlank
        @field:NotNull
        val posting : String,
        var user : User? = null
) {
}