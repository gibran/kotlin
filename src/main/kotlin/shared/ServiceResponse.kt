package shared

class ServiceResponse<T> {

    private var errorKind : KindServiceResponse? = null
    private var errorMessage : String? = null
    private var errors : ArrayList<ErrorMessage>? = null

    var Result : T? = null

    fun setResult(result : T) : ServiceResponse<T> {

        errorMessage = null
        errorKind = null
        errors = null
        Result = result

        return this
    }

    private fun setError(errorMessage : String, errorKind : KindServiceResponse, errors : ArrayList<ErrorMessage>? = null) : ServiceResponse<T>
    {
        this.errorMessage = errorMessage
        this.errorKind = errorKind
        this.errors = errors

        return this
    }

    fun setInternalServerError(errorMessage : String, errors : ArrayList<ErrorMessage>? = null) : ServiceResponse<T>
    {
        return setError(errorMessage, KindServiceResponse.INTERNAL_SERVER_ERROR, errors)
    }

    fun setBadRequest(errorMessage : String, errors : ArrayList<ErrorMessage>? = null) : ServiceResponse<T>
    {
        return setError(errorMessage, KindServiceResponse.BAD_REQUEST, errors)
    }

    fun setNotFound(errorMessage : String, errors : ArrayList<ErrorMessage>? = null) : ServiceResponse<T>
    {
        return setError(errorMessage, KindServiceResponse.NOT_FOUND, errors)
    }

    fun getErrorKind() : KindServiceResponse?
    {
        return errorKind
    }

    fun getMessage() : String {
        return "teste"
    }

    fun Success() : Boolean
    {
        return errorKind == null
    }

}

