package distributed.order.management.Controller.Dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateUserDto(
    @NotBlank(message = "O nome não pode ser vazio")
    val name: String,

    @NotBlank(message = "O email não pode ser vazio")
    @Email(message = "Formato de email inválido")
    val email: String,

    @NotBlank(message = "A senha não pode ser vazia")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    val password: String
)