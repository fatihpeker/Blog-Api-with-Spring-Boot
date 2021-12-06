package tr.folksdev.kdgms.dto

import tr.folksdev.kdgms.model.BlogPost
import tr.folksdev.kdgms.model.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateCommentRequest(
        @field:NotBlank
        @field:NotNull
        val remark : String,
        val blogPost_id: String,
        var user: User?
) {
}