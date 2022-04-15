package com.hse.dfa.backend.controller.error;

import com.hse.dfa.backend.exceptions.authentication.NotAuthenticatedRequestException;
import com.hse.dfa.backend.exceptions.authentication.UsernameIsAlreadyExistException;
import com.hse.dfa.backend.exceptions.contract.UserEthereumAddressAbsentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

import static com.hse.dfa.backend.controller.error.ApiError.createError;

@ControllerAdvice
public class RestExceptionResolver extends ResponseEntityExceptionHandler {
    //TODO Добавить логирование
    //TODO Добавить кастомные ошибки на отсутствие сущьностей
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ApiError> handleNoSuchElementException(NoSuchElementException ex, HttpServletRequest request) {
        return innerHandleException(HttpStatus.NOT_FOUND, "Сущьность не найдена!");
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<ApiError> handleAuthenticationServiceException(AuthenticationServiceException ex, HttpServletRequest request) {
        return innerHandleException(HttpStatus.UNAUTHORIZED, "Неправильный логин или пароль!");
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        return innerHandleException(HttpStatus.UNAUTHORIZED, "Неправильный логин или пароль!");
    }

    @ExceptionHandler(UsernameIsAlreadyExistException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<ApiError> handleUsernameIsAlreadyExistException(UsernameIsAlreadyExistException ex, HttpServletRequest request) {
        return innerHandleException(HttpStatus.UNAUTHORIZED, ex.getUserMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request) {
        return innerHandleException(HttpStatus.NOT_FOUND, "Пользователь с таким username не найден!");
    }

    @ExceptionHandler(NotAuthenticatedRequestException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<ApiError> handleNotAuthenticatedRequestException(NotAuthenticatedRequestException ex, HttpServletRequest request) {
        return innerHandleException(HttpStatus.UNAUTHORIZED, ex.getUserMessage());
    }

    @ExceptionHandler(UserEthereumAddressAbsentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ApiError> handleUserEthereumAddressAbsentException(UserEthereumAddressAbsentException ex, HttpServletRequest request) {
        return innerHandleException(HttpStatus.NOT_FOUND, ex.getUserMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ResponseEntity<ApiError> handleThrowable(Exception ex, HttpServletRequest request) {
        return innerHandleException(
            HttpStatus.INTERNAL_SERVER_ERROR,
//            ex.getMessage() + '\n' +
//                Arrays.stream(ex.getStackTrace())
//                    .map(Objects::toString)
//                    .reduce("", (s1, s2) -> s1 + '\n' + s2)
            "Неизвестная ошибка! Пожалуйста, обратитесь к администратору!"
        );
    }

    private ResponseEntity<ApiError> innerHandleException(HttpStatus status, String message) {
        final var error = createError(status, message);
        return new ResponseEntity<>(error, status);
    }
}
