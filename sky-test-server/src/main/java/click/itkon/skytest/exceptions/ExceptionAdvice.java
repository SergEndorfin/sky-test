package click.itkon.skytest.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation") // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {
        // TODO: handle specific cases. e.g. what if we don't want to delete an User which has some project?
    }
}
