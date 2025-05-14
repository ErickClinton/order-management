package distributed.order.management.Service

import distributed.order.management.Contract.IUserContract
import distributed.order.management.Controller.Dto.CreateUserDto
import distributed.order.management.Controller.Dto.LoginDto
import distributed.order.management.Controller.Dto.LoginResponseDto
import distributed.order.management.Entity.UserEntity
import distributed.order.management.Repository.UserRepository
import distributed.order.management.exceptions.User.UserAlreadyExistException
import distributed.order.management.exceptions.User.UserBadCredentialsException
import distributed.order.management.exceptions.User.UserNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant


@Service
class UserService(private val userRepository: UserRepository,
                  private val bCryptPasswordEncoder: BCryptPasswordEncoder,
                  private val jwtEncoder: JwtEncoder) : IUserContract {

    override fun saveUser(user: CreateUserDto): UserEntity {

        userRepository.findByEmail(user.email)?.let {
            throw UserAlreadyExistException("User with email ${user.email} already exists")
        }

        val userEntity = UserEntity(name = user.name, email = user.email, password = bCryptPasswordEncoder.encode(user.password))
        return this.userRepository.save(userEntity)
    }

    override fun login(loginDto: LoginDto): LoginResponseDto {
        val user = this.userRepository.findByEmail(loginDto.email) ?:
            throw UserNotFoundException("User not found: ${loginDto.email}")

        user.validatePassword(loginDto.password,bCryptPasswordEncoder)

        val now = Instant.now()
        val expiresIn = 300L

        val claims = JwtClaimsSet.builder()
            .issuer("server")
            .subject(user.id.toString())
            .expiresAt(now.plusSeconds(expiresIn))
            .issuedAt(now)
            .build()

        val jwtValue: String = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue()

        return LoginResponseDto(jwtValue, expiresIn)

    }

    fun UserEntity.validatePassword(password: String, bcrypt: BCryptPasswordEncoder) {
        when {
            !bcrypt.matches(password, this.password) -> {
                throw UserBadCredentialsException("Password or email incorrect")
            }
        }
    }}