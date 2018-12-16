package com.spring.examples.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Quote implements Serializable {
    private static final long serialVersionUID = 7070149906293202080L;

    @NonNull
    private String quote;
    @NonNull
    private String author;
    private String createdDt;
    private String updateDt;
}
