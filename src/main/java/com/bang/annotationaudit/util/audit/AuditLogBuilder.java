package com.bang.annotationaudit.util.audit;

import jakarta.servlet.http.HttpServletRequest;

import java.text.MessageFormat;
import java.util.stream.Collectors;

public class AuditLogBuilder {
    private final String AUDIT_LOG_TEMPLATE = "method={0}, uri={1}, " +
            "parameters=[{2}], statusCode={3}, requestBody={4}, responseBody={5}";

    private String method;
    private String uri;
    private String parameters;
    private int statusCode;
    private String requestBody;
    private String responseBody;

    public AuditLogBuilder statusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public AuditLogBuilder requestBody(String requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public AuditLogBuilder responseBody(String responseBody) {
        this.responseBody = responseBody;
        return this;
    }

    public AuditLogBuilder(HttpServletRequest request) {
        this.method = request.getMethod();
        this.uri = request.getRequestURI();
        this.parameters = request
                .getParameterMap()
                .entrySet()
                .stream()
                .map(entry -> String.format("%s=%s", entry.getKey(), String.join(",", entry.getValue())))
                .collect(Collectors.joining("&"));
    }

    public String build() {
        return MessageFormat.format(AUDIT_LOG_TEMPLATE, method, uri, parameters, statusCode, requestBody, responseBody);
    }
}
