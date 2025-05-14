package distributed.order.management.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail


abstract class UserException(message: String?) : RuntimeException(message) {

    open fun toProblemDetail(): ProblemDetail {
        val pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        pd.title = "Ops,our mistake. Internal Server Error"
        pd.detail = "contact support"

        return pd
    }
}