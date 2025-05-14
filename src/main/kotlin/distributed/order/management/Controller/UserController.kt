package distributed.order.management.Controller

import distributed.order.management.Contract.IUserContract
import distributed.order.management.Controller.Dto.CreateUserDto
import distributed.order.management.Controller.Dto.LoginDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val IUserContract: IUserContract) {

    @PostMapping("create")
    fun hello(@RequestBody @Valid user:CreateUserDto) = this.IUserContract.saveUser(user)

    @PostMapping("login")
    fun login(@RequestBody login: LoginDto ) = this.IUserContract.login(login)
}