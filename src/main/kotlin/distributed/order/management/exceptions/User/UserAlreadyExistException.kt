package distributed.order.management.exceptions.User

import distributed.order.management.exceptions.UserException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

class UserAlreadyExistException(private val detail: String) : UserException(detail) {
    override fun toProblemDetail(): ProblemDetail {
        val pd = ProblemDetail.forStatus(HttpStatus.CONFLICT)
        pd.title = "User Already Exist"
        pd.detail = detail
        return pd
    }
}