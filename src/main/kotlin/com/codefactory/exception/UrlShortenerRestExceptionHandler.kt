package com.codefactory.exception

import org.hibernate.validator.internal.engine.path.PathImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.multipart.support.MissingServletRequestPartException
import javax.validation.ConstraintViolationException

@ControllerAdvice
class UrlShortenerRestExceptionHandler {

    @ExceptionHandler(UrlNotFoundException::class)
    fun handle(e: UrlNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(e.message)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun missingServletRequestParameterException(e: MissingServletRequestParameterException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Missing parameter '" + e.parameterName + "'")
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun methodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Parameter type mismatch field '" + e.parameter.parameterName + "'")
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("HttpMessage not readable: '" + e.message + "'")
    }

    @ExceptionHandler(MissingServletRequestPartException::class)
    fun missingServletRequestPartException(e: MissingServletRequestPartException): ResponseEntity<String>? {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Parameter validation failed for field " + e.requestPartName)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationException(e: ConstraintViolationException): ResponseEntity<String>? {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Parameter validation failed for field " +
                    e.constraintViolations.stream()
                        .map { cv -> (cv.propertyPath as PathImpl).leafNode.asString() + ": " + cv.message }
                        .findFirst()
                        .orElse(e.message)
            )
    }
}