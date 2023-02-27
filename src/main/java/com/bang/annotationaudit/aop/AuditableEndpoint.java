package com.bang.annotationaudit.aop;

import com.bang.annotationaudit.model.AuditEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditableEndpoint {
    AuditEvent auditEvent();
}
