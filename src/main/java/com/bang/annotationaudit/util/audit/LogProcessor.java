package com.bang.annotationaudit.util.audit;

public interface LogProcessor {
    String processRequestLog(Object[] arguments);
    String processResponseLog(Object response);
    int processStatusCode(Object response);
}
