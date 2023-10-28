package devs2blu.hackweek.eventmanager.exceptions.handlers;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.ErrorDto;
import devs2blu.hackweek.eventmanager.dtos.FieldErrorDto;
import devs2blu.hackweek.eventmanager.exceptions.EmailAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(
                ErrorDto.builder()
                        .message(ErrorMessages.VALIDATION_ERROR)
                        .status(HttpStatus.BAD_REQUEST.name())
                        .errors(
                                ex.getBindingResult().getFieldErrors().stream()
                                        .map(fieldError -> new FieldErrorDto(fieldError.getField(), fieldError.getDefaultMessage()))
                                        .toList()
                        )
                        .build()
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.name())
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .build()
        );
    }

    // Person
    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleCpfAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.toString())
                        .build()
        );
    }
}
