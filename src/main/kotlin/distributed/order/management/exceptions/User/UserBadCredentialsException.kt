package distributed.order.management.exceptions.User

import distributed.order.management.exceptions.UserException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

class UserBadCredentialsException(private val detail: String) : UserException(detail) {
    override fun toProblemDetail(): ProblemDetail {
        val pd = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED)
        pd.detail = "Verify your email or password"
        pd.detail = detail

        return pd
    }
}