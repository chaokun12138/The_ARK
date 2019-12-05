package com.ark.exception;

import com.ark.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArkException extends RuntimeException {
    public ExceptionEnum exceptionEnum;
}
