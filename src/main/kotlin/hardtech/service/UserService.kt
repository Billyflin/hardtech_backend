package hardtech.service

import hardtech.dto.UserDto
import hardtech.dto.UserDto.Companion.from
import hardtech.entity.Authority
import hardtech.entity.User
import hardtech.exception.DuplicateMemberException
import hardtech.exception.NotFoundMemberException
import hardtech.repository.UserRepository
import hardtech.util.SecurityUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
    ) {
    @Transactional
    fun signup(userDto: UserDto): UserDto {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.username!!).orElse(null) != null) {
            throw DuplicateMemberException("이미 가입되어 있는 유저입니다.")
        }

        val authority = Authority("ROLE_USER")

        val user = User(
            username = userDto.username,
            password = passwordEncoder.encode(userDto.password),
            nickname = userDto.nickname,
            authorities = setOf(authority),
            isActivated = true
        )

        return from(userRepository.save(user))
    }

    @Transactional(readOnly = true)
    fun getUserWithAuthorities(username: String): UserDto {
        return from(
            userRepository.findOneWithAuthoritiesByUsername(username)
            .orElse(null)
        )
    }

    @get:Transactional(readOnly = true)
    val myUserWithAuthorities: UserDto
        get() = from(
            SecurityUtil.currentUsername
                .flatMap {
                    username: String -> userRepository.findOneWithAuthoritiesByUsername(username)
                }
                .orElseThrow {
                    throw NotFoundMemberException("Member not found")
                }
        )
}