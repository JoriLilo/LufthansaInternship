package de.lhind.internship.mini.project.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


//    // 500 INTERNAL SERVER ERROR - catch-all, generic message per "Best Practices" slide
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
//        ErrorResponse body = new ErrorResponse();
//
//                body.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//                body.setMessage(ex.getMessage());
//                body.setPath(request.getRequestURI());
//
//
//
//
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
//    }


}
