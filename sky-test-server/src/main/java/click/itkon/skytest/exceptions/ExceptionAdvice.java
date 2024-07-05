package click.itkon.skytest.exceptions;

import click.itkon.apifirst.model.ProblemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDto> conflict(Exception ex, WebRequest request) {
        var title = "Data Integrity Violation";

        log.error("{}: {}", title, ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(fillProblemDetailsAndGet(ex, request)
                .title(title)
                .status(HttpStatus.CONFLICT.value())
                .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDto> notFound(Exception ex, WebRequest request) {
        var title = "Not found";

        log.error("{}: {}", title, ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fillProblemDetailsAndGet(ex, request)
                .title("Not Found")
                .status(HttpStatus.NOT_FOUND.value())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDto> unexpected(Exception ex, WebRequest request) {
        var title = "Unexpected exception";

        log.error("{}: {}", title, ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fillProblemDetailsAndGet(ex, request)
                .title(title)
                .status(HttpStatus.NOT_FOUND.value())
                .build());
    }


    private ProblemDto.ProblemDtoBuilder fillProblemDetailsAndGet(Exception ex, WebRequest request) {
        return ProblemDto.builder()
                .detail(ex.getMessage())
                .instance(request.getDescription(false));
    }
}
