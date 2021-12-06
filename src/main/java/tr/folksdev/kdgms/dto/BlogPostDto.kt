package tr.folksdev.kdgms.dto

import javax.validation.constraints.NotBlank

data class BlogPostDto(
        @field:NotBlank
        var id : String,
        var posting : String,
        @field:NotBlank
        var user_id : String
) {
}