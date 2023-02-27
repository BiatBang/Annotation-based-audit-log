package com.bang.annotationaudit.util.audit;

import org.springframework.http.ResponseEntity;

public interface ControllerLogProcessor extends LogProcessor {
    default int processStatusCode(Object response) {
        return ((ResponseEntity) response).getStatusCode().value();
    }
}
