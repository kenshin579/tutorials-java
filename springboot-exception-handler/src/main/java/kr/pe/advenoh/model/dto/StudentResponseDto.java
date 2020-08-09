package kr.pe.advenoh.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDto {
    private Long id;
    private String name;
    private String mobileNumber;
    private String address;
}
