package com.example.jobsearchtw.network.data.api

enum class ErrorType(val message: String) {
    //   Если сообщения понадобятся, согласовать тексты и вынести в ресурсы
    NO_CONNECTION("Проверьте соединение с интернетом"),
    NOT_FOUND("Ничего не нашлось"),
    BAD_REQUEST("Что-то пошло не так, уже иправляем!"),
    SERVER_ERROR("Что-то пошло не так, уже иправляем!"),
    UNKNOWN_ERROR("Что-то пошло не так, уже иправляем!"),
}

fun Int.mapToErrorType(): ErrorType {
    return when (this) {
        NetworkParams.NO_CONNECTION_CODE -> ErrorType.NO_CONNECTION
        NetworkParams.BAD_REQUEST_CODE -> ErrorType.BAD_REQUEST
        NetworkParams.NOT_FOUND_CODE -> ErrorType.NOT_FOUND
        NetworkParams.SERVER_ERROR_CODE -> ErrorType.SERVER_ERROR
        else -> ErrorType.UNKNOWN_ERROR
    }
}