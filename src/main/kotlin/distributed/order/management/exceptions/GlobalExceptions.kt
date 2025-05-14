package distributed.order.management.exceptions

import distributed.order.management.exceptions.dto.InvalidParamDto
import org.springframework.http.ProblemDetail
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(UserException::class)
    fun handleUserException(e: UserException): ProblemDetail {
        return e.toProblemDetail()
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleException(e: MethodArgumentNotValidException): ProblemDetail {
        val pd = ProblemDetail.forStatus(400)
        val invalidParam = e.fieldErrors
            .stream()
            .map<Any> { fe: FieldError -> fe.defaultMessage?.let { InvalidParamDto(fe.field, it) } }
            .toList()

        pd.title = "Invalid request param"
        pd.detail = "There is invalid fields on the request"
        pd.setProperty("invalid params", invalidParam)

        return pd
    }
}