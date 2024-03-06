package com.github.Rharhuky.api.resourcers.exceptions;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class StandardError {

    private LocalDateTime timestamp;
    private Integer status;
    private String details;
    private String path;
}
