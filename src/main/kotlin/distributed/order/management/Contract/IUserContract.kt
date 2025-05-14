package distributed.order.management.Contract

import distributed.order.management.Controller.Dto.CreateUserDto
import distributed.order.management.Controller.Dto.LoginDto
import distributed.order.management.Controller.Dto.LoginResponseDto
import distributed.order.management.Entity.UserEntity

interface IUserContract {
    fun saveUser(user: CreateUserDto): UserEntity
    fun login(loginDto: LoginDto): LoginResponseDto
}