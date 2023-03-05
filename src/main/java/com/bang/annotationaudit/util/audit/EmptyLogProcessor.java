package com.bang.annotationaudit.util.audit;

public interface EmptyLogProcessor extends LogProcessor {
    default String processRequestLog(Object[] arguments) {
        return null;
    }

    default String processResponseLog(Object response) {
        return null;
    }
}
