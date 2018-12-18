package com.spring.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Quote {
    @NonNull Long id;
    @NonNull String author;
    @NonNull String quote;
}
