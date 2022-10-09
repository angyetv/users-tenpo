package cl.challenge.tenpo.exception.controller;

import cl.challenge.tenpo.dtos.ResponseDTO;
import cl.challenge.tenpo.exception.ExternalServiceException;
import cl.challenge.tenpo.exception.InputFieldsErrorException;
import cl.challenge.tenpo.exception.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleRoleNotFoundException() {
        return ResponseEntity.status(NO_CONTENT)
                .body(new ResponseDTO(NO_CONTENT.value(), "Role not found."));
    }

    @ExceptionHandler(InputFieldsErrorException.class)
    public ResponseEntity<ResponseDTO> handleInputFieldsErrorException() {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ResponseDTO(422, "Please check the fields and try again."));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDTO> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ResponseDTO(BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ApiRequestExceptionHandler> handleExternalServiceException(ExternalServiceException e) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiRequestExceptionHandler(
                        e.getMessage(), e, BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z"))));
    }
}
