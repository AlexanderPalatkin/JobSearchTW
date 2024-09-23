package com.example.jobsearchtw.network.data.api

import com.example.jobsearchtw.core.domain.model.ErrorType

fun Int.mapToErrorType(): ErrorType {
    return when (this) {
        NetworkParams.NO_CONNECTION_CODE -> ErrorType.NO_CONNECTION
        NetworkParams.BAD_REQUEST_CODE -> ErrorType.BAD_REQUEST
        NetworkParams.NOT_FOUND_CODE -> ErrorType.NOT_FOUND
        NetworkParams.SERVER_ERROR_CODE -> ErrorType.SERVER_ERROR
        else -> ErrorType.UNKNOWN_ERROR
    }
}