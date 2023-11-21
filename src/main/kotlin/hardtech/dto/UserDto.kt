package hardtech.dto

import hardtech.entity.User
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UserDto(
    @field:NotNull @field:Size(min = 3, max = 50) var username: String? = null,

    @field:NotNull @field:Size(min = 3, max = 100) var password: String? = null,

    @field:NotNull @field:Size(min = 3, max = 50) var nickname: String? = null,

    var email: String? = null, var phone: String? = null,

    var address: String? = null,

    var city: String? = null,

    var country: String? = null,

    var isActivated: Boolean = false,

    var authorityDtoSet: Set<AuthorityDto>? = null
) {
    companion object {
        fun from(user: User): UserDto {
            return user.run {
                UserDto(username = username,
                    nickname = nickname,
                    email = email,
                    phone = phone,
                    address = address,
                    city = city,
                    country = country,
                    isActivated = isActivated,
                    authorityDtoSet = authorities!!.map { authority ->
                            AuthorityDto(authority.authorityName)
                        }.toSet())
            }
        }
    }
}
