package hardtech.service

import hardtech.entity.Authority
import hardtech.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Component("userDetailsService")
class CustomUserDetailsService(
    private val userRepository: UserRepository
    ) : UserDetailsService {
    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findOneWithAuthoritiesByUsername(username)
            .map { user: hardtech.entity.User -> createUser(username, user) }
            .orElseThrow { UsernameNotFoundException("$username -> No se encontró") }
    }

    private fun createUser(username: String, user: hardtech.entity.User): User {
        if (!user.isActivated) {
            throw RuntimeException("$username -> No está activado")
        }

        val grantedAuthorities = user.authorities!!.stream()
            .map { authority: Authority -> SimpleGrantedAuthority(authority.authorityName) }
            .collect(Collectors.toList())

        return User(
            user.username,
            user.password,
            grantedAuthorities
        )
    }
}