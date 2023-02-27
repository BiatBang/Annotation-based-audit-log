package com.bang.annotationaudit.model;

import com.bang.annotationaudit.util.audit.CreateBookLogProcessor;
import com.bang.annotationaudit.util.audit.LogProcessor;

public enum AuditEvent {
    CREATE_BOOK(new CreateBookLogProcessor());

    final LogProcessor logProcessor;

    AuditEvent(LogProcessor logProcessor) {
        this.logProcessor = logProcessor;
    }

    public String processRequest(Object[] request) {
        return this.logProcessor.processRequestLog(request);
    }

    public String processResponse(Object response) {
        return this.logProcessor.processResponseLog(response);
    }

    public int processStatusCode(Object response) {
        return this.logProcessor.processStatusCode(response);
    }
}
