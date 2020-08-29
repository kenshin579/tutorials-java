package com.advenoh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Vod {
    private Long vodId;
    private String vodTitle;
    private Integer dealNo;

}
