package com.bang.annotationaudit.model;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Book {
    @Nullable
    private Long id;
    private String name;
    private List<String> authors;
    private String publisher;
}
