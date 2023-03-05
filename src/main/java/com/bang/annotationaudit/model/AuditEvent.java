package com.bang.annotationaudit.model;

import com.bang.annotationaudit.util.audit.CreateBookLogProcessor;
import com.bang.annotationaudit.util.audit.DeleteBookLogProcessor;
import com.bang.annotationaudit.util.audit.GetAllBooksLogProcessor;
import com.bang.annotationaudit.util.audit.GetBookLogProcessor;
import com.bang.annotationaudit.util.audit.LogProcessor;
import com.bang.annotationaudit.util.audit.UpdateBookLogProcessor;

public enum AuditEvent {
    CREATE_BOOK(new CreateBookLogProcessor()),
    GET_ALL_BOOKS(new GetAllBooksLogProcessor()),
    GET_BOOK(new GetBookLogProcessor()),
    UPDATE_BOOK(new UpdateBookLogProcessor()),
    DELETE_BOOK(new DeleteBookLogProcessor());

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
