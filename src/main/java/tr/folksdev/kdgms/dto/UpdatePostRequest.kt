package tr.folksdev.kdgms.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UpdatePostRequest(
        @field:NotBlank
        @field:NotNull
        val id : String,
        @field:NotBlank
        @field:NotNull
        val posting : String
) {
}