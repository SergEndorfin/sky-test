package click.itkon.skytest.exceptions;

public class NotFoundException extends RuntimeException {

    public static final String ERROR_MESSAGE = "Not found by ";

    public NotFoundException(String message) {
        super(message);
    }
}
