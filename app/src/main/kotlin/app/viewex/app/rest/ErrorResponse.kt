package app.viewex.app.rest

abstract class ErrorResponse(
    val httpStatus: HttpStatus,
    val message: String,
    val data: Any?
) {

    // 400
    class BadRequest(
        message: String? = null,
        data: Any? = null
    ) : ErrorResponse(
        HttpStatus.BadRequest,
        message ?: HttpStatus.BadRequest.defaultMessage,
        data
    )

    //401
    class Unauthorized(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.Unauthorized,
        message ?: HttpStatus.Unauthorized.defaultMessage,
        data
    )

    // 403
    class Forbidden(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.Forbidden,
        message ?: HttpStatus.Forbidden.defaultMessage,
        data
    ) {
        companion object {
            val Empty = Forbidden()
        }

    }

    // 404
    class NotFound(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.NotFound,
        message ?: HttpStatus.NotFound.defaultMessage,
        data
    ) {

        companion object {
            val Empty = NotFound()
        }

    }

    // 405
    class MethodNotAllowed(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.MethodNotAllowed,
        message ?: HttpStatus.MethodNotAllowed.defaultMessage,
        data
    )

    // 406
    class NotAcceptable(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.NotAcceptable,
        message ?: HttpStatus.NotAcceptable.defaultMessage,
        data
    )

    // 408
    class RequestTimeout(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.RequestTimeout,
        message ?: HttpStatus.RequestTimeout.defaultMessage,
        data
    )

    // 415
    class UnsupportedMediaType(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.UnsupportedMediaType,
        message ?: HttpStatus.UnsupportedMediaType.defaultMessage,
        data
    )

    // 418
    class IAmTeapot(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.IAmTeapot,
        message ?: HttpStatus.IAmTeapot.defaultMessage,
        data
    )

    // 429
    class TooManyRequests(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.TooManyRequests,
        message ?: HttpStatus.TooManyRequests.defaultMessage,
        data
    )

    // 500
    class InternalServerError(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.InternalServerError,
        message ?: HttpStatus.InternalServerError.defaultMessage,
        data
    )

    // 502
    class BadGateway(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.BadGateway,
        message ?: HttpStatus.BadGateway.defaultMessage,
        data
    )

    // 503
    class ServiceUnavailable(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.ServiceUnavailable,
        message ?: HttpStatus.ServiceUnavailable.defaultMessage,
        data
    )

    // 504
    class GatewayTimeout(
        message: String? = null,
        data: Any? = null,
    ) : ErrorResponse(
        HttpStatus.GatewayTimeout,
        message ?: HttpStatus.GatewayTimeout.defaultMessage,
        data
    )
}
