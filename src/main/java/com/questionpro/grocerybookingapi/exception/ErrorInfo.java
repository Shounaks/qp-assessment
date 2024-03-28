package com.questionpro.grocerybookingapi.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorInfo {
    private final Long errorId;
    private final String errorMessage;
}