package click.itkon.skytest.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Email must be unique") // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict(Exception ex) {
        log.error("Error message: {}", ex.getMessage());
    }
}
