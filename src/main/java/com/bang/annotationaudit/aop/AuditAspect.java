package com.bang.annotationaudit.aop;

import com.bang.annotationaudit.model.AuditEvent;
import com.bang.annotationaudit.util.audit.AuditLogBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class AuditAspect {
    @Around("@annotation(auditableEndpoint)")
    public Object appendEventLog(ProceedingJoinPoint joinPoint, AuditableEndpoint auditableEndpoint) throws Throwable {
        Object[] arguments = joinPoint.getArgs();
        Object response = joinPoint.proceed();
        logAuditEvent(auditableEndpoint.auditEvent(), arguments, response);
        return response;
    }

    private void logAuditEvent(AuditEvent event, Object[] arguments, Object response) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestLog = event.processRequest(arguments);
        String responseLog = event.processResponse(response);
        int statusCode = event.processStatusCode(response);
        String auditLog = new AuditLogBuilder(request)
                .requestBody(requestLog)
                .responseBody(responseLog)
                .statusCode(statusCode)
                .build();
        log.info(auditLog);
    }
}
