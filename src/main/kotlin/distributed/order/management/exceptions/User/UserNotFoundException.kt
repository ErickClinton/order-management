package distributed.order.management.exceptions.User

import distributed.order.management.exceptions.UserException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

class UserNotFoundException(private val detail: String) : UserException(detail) {
    override fun toProblemDetail(): ProblemDetail {
        val pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND)
        pd.title = "User not found"
        pd.detail = detail

        return pd
    }
}