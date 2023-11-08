package hardtech.hardtech_backend.models

import java.io.Serializable

/**
 * DTO for {@link hardtech.hardetech_backend.users.User}
 */
data class UserDto(val id: Int? = null, val name: String? = null, val email: String? = null) : Serializable