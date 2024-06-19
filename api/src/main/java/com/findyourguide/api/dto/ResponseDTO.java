package com.findyourguide.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public  class ResponseDTO <T> {
    private Boolean success;
    private String message;
    private T data;

}
