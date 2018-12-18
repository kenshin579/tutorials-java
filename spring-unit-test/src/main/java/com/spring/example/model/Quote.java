package com.spring.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Quote {
    @NonNull String author;
    @NonNull String quote;
}
