package extensions

import io.javalin.*
import shared.KindServiceResponse
import shared.ServiceResponse

fun <T> ServiceResponse<T>.toResult() : T {

    val message = this.getMessage()

    when(this.getErrorKind()){
        KindServiceResponse.INTERNAL_SERVER_ERROR -> throw InternalServerErrorResponse()
        KindServiceResponse.BAD_REQUEST -> throw BadRequestResponse()
        KindServiceResponse.UNAUTHORIZED -> throw UnauthorizedResponse()
        KindServiceResponse.FORBIDDEN -> throw ForbiddenResponse()
        KindServiceResponse.NOT_FOUND -> throw NotFoundResponse()
        null -> return this.Result!!
    }
}