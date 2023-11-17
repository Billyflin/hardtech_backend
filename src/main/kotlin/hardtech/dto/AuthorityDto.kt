package hardtech.dto

import hardtech.entity.Authority
import hardtech.entity.User

data class AuthorityDto(
    var authorityName: String? = null
){
    companion object {
        fun from(authority: Authority): AuthorityDto {
            return authority.run {
                AuthorityDto(
                    authorityName = authorityName
                )
            }
        }
    }
}