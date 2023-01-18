package app.viewex.app.rest

import app.viewex.core.exception.AnonymousClassException
import app.viewex.core.type.IntType
import app.viewex.core.util.StringUtils
import app.viewex.core.util.firstToUpper

abstract class HttpStatus(
    value: Int,
    defaultMessage: String? = null
) : IntType(value, 100, 599) {

    val defaultMessage = defaultMessage ?: this::class.simpleName?.let {
        StringUtils.camelCaseToString(it).firstToUpper()
    } ?: throw AnonymousClassException(HttpStatus::class)

    object BadRequest : HttpStatus(400)

    object Unauthorized : HttpStatus(401)

    object Forbidden : HttpStatus(403)

    object NotFound : HttpStatus(404)

    object MethodNotAllowed : HttpStatus(405)

    object NotAcceptable : HttpStatus(406)

    object RequestTimeout : HttpStatus(408)

    object UnsupportedMediaType : HttpStatus(415)

    object IAmTeapot : HttpStatus(418)

    object TooManyRequests : HttpStatus(429)

    object InternalServerError : HttpStatus(500)

    object BadGateway : HttpStatus(502)

    object ServiceUnavailable : HttpStatus(503)

    object GatewayTimeout : HttpStatus(504)
}
