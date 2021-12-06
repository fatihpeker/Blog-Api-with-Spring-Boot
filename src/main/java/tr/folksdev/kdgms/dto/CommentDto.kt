package tr.folksdev.kdgms.dto

import javax.validation.constraints.NotBlank

data class CommentDto(
        @field:NotBlank
        var id : String,
        var remark : String,
        var user_id : String,
        var post_id : String
) {
}