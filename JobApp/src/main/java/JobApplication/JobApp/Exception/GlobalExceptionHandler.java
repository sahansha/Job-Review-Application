package JobApplication.JobApp.Exception;

import JobApplication.JobApp.Model.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleAllException(Exception ex, WebRequest request)throws Exception
    {
        ExceptionDetails exceptionDetails=ExceptionDetails.builder()
                                            .timeStamp(Instant.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleNotFoundException(Exception ex, WebRequest request)throws Exception
    {
        ExceptionDetails exceptionDetails=ExceptionDetails.builder()
                .timeStamp(Instant.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }
}
