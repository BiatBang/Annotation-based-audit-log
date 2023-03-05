package com.bang.annotationaudit.advice;

import com.bang.annotationaudit.aop.AuditableEndpoint;
import com.bang.annotationaudit.model.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.bang.annotationaudit.model.AuditEvent.ERROR;

@ControllerAdvice
public class ErrorAdvice {

    @AuditableEndpoint(auditEvent = ERROR)
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity onException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
