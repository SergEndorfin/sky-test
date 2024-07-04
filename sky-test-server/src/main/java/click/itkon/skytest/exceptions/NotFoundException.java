package click.itkon.skytest.exceptions;

public class NotFoundException extends RuntimeException {

    public static final String ERROR_MESSAGE = "Source not found";

    public NotFoundException(String sourceId) {
        super(ERROR_MESSAGE + " by: " + sourceId);
    }
}
