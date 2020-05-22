package io.github.chavesrodolfo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.github.chavesrodolfo.model.MessageResponse;

@ControllerAdvice
public class ExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidPasswordPatternException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
    MessageResponse invalidPasswordPatternHandler(InvalidPasswordPatternException e) {
        return new MessageResponse(InvalidPasswordPatternException.class.getSimpleName(), e.getMessage());
    }
}