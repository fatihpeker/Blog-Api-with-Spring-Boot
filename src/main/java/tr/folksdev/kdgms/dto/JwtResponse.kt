package tr.folksdev.kdgms.dto

import javax.validation.constraints.NotBlank

data class JwtResponse @JvmOverloads constructor (

        var token : String,
        var type : String? = "Bearer",
        @field:NotBlank
        var id : String,
        @field:NotBlank
        var username : String,

        ) {
}