package assesment.apiportal.publisher.demo.controller;

import assesment.apiportal.publisher.demo.exception.ApiProvisionErrorResponce;
import assesment.apiportal.publisher.demo.exception.ErrorResponse;
import assesment.apiportal.publisher.demo.exception.FileStorageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    @ExceptionHandler(ApiProvisionErrorResponce.class)
    public ResponseEntity<ErrorResponse> deviceNotFoundExceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(9000);
        error.setDesc(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorResponse> fileStorageExceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(9100);
        error.setDesc(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
}
