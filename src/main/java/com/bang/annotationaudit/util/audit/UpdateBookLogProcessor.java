package com.bang.annotationaudit.util.audit;

import com.bang.annotationaudit.model.Book;

public class UpdateBookLogProcessor implements ControllerLogProcessor {
    @Override
    public String processRequestLog(Object[] arguments) {
        Book book = (Book) arguments[1];
        return book.getName() + "-" + book.getPublisher();
    }

    @Override
    public String processResponseLog(Object response) {
        return null;
    }
}
