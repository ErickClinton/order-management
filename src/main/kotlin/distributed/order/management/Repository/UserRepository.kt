package distributed.order.management.Repository

import distributed.order.management.Entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
}