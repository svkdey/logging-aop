package com.demo.app;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private String name;
    private String Author;
}
