# Annotation based audit log

This is a demo application that generates audit log by adding annotations on controller endpoints.

## Use case
If the application needs to store audit logs for some endpoints, and some endpoints only need partial information to log. This method may be helpful.

## How to use
1. Add a new LogProcessor implementation for the endpoint. Implement the request, response and statusCode logic
2. Add the event to AuditEvent class, and add the new LogProcessor as the constructor argument
3. Add @AuditableEndpoint to the endpoint method, and specify the event type

## Example
When calling `create a book` endpoint: 

```
POST http://localhost:8080/books

{
    "name": "Harry Potter and the Sorcerer's Stone",
    "authors": ["J.K. Rowling"],
    "publisher": "Bloomsbury"
}
```

generated log:
```
method=POST, uri=/books, parameters=[], statusCode=201, requestBody=Harry Potter and the Sorcerer's Stone-Bloomsbury, responseBody=null
```

## Limitation
With this method, it might be hard to log the specified request body if there's exception thrown. The error handler cannot detect which audit event is contained in the current request.