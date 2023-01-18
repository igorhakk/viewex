package app.viewex.app.rest

interface HttpResponse {

    class Success(
        val result: Any = ResultOK
    ) : HttpResponse {
        companion object {
            const val ResultOK = "OK"
        }
    }

    class Failed(
        val error: ErrorResponse,
    ) : HttpResponse

}
