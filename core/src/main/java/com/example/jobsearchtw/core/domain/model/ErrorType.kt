package com.example.jobsearchtw.core.domain.model

enum class ErrorType(val message: String) {
    //   Если сообщения понадобятся, согласовать тексты и вынести в ресурсы
    NO_CONNECTION("Проверьте соединение с интернетом"),
    NOT_FOUND("Ничего не нашлось"),
    BAD_REQUEST("Что-то пошло не так, уже иправляем!"),
    SERVER_ERROR("Что-то пошло не так, уже иправляем!"),
    UNKNOWN_ERROR("Что-то пошло не так, уже иправляем!"),
}