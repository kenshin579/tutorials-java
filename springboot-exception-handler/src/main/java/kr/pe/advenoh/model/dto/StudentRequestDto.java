package kr.pe.advenoh.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StudentRequestDto {
    @NotNull
    private String name;

    @NotNull
    private String mobileNumber;

    @NotNull
    private String address;
}
