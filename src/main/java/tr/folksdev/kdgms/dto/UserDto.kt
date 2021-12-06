package tr.folksdev.kdgms.dto

import javax.validation.constraints.NotBlank

data class UserDto @JvmOverloads constructor(
        @field:NotBlank
        var id : String,
        @field:NotBlank
        var username : String,
        var name : String,
        var surname : String
) {
}