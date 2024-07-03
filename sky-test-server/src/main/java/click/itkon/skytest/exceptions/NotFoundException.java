package click.itkon.skytest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static click.itkon.skytest.exceptions.NotFoundException.ERROR_MESSAGE;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = ERROR_MESSAGE)
public class NotFoundException extends RuntimeException {

    public static final String ERROR_MESSAGE = "Source not found";

    public NotFoundException(String sourceId) {
        super(ERROR_MESSAGE + " by: " + sourceId);
    }
}
