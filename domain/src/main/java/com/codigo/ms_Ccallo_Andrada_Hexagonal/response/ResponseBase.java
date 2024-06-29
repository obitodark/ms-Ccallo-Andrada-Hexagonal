package com.codigo.ms_Ccallo_Andrada_Hexagonal.response;

import lombok.*;


import java.util.Optional;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBase {
    private int code;
    private String message;
    private Optional data;
}
