package de.lhind.internship.mini.project.exception;

import de.lhind.internship.mini.project.exception.ErrorResponse;
import de.lhind.internship.mini.project.exception.RoomNotAvailableException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 500 INTERNAL SERVER ERROR - catch-all, generic message per "Best Practices" slide
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request)
    {
        ErrorResponse body = ErrorResponse.builder()
                .status (HttpStatus. INTERNAL_SERVER_ERROR. value())
                .error(HttpStatus. INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("An unexpected error occurred")
                .path( request. getRequestURI ())
                    . build();
        return ResponseEntity. status (HttpStatus. INTERNAL_SERVER_ERROR)
                .body (body) ;
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleRoomNotAvailable(RoomNotAvailableException ex, HttpServletRequest request) {
        ErrorResponse body = ErrorResponse.builder()
                .status (HttpStatus. INTERNAL_SERVER_ERROR. value())
                .error(HttpStatus. INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("The room is not available")
                .path( request. getRequestURI ())
                . build();
        return ResponseEntity. status (HttpStatus. INTERNAL_SERVER_ERROR)
                .body (body) ;
    }






}
