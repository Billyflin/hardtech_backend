package hardtech.hardtech_backend.repository

import hardtech.hardtech_backend.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int>