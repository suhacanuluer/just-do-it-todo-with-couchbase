package org.suhacan.justdoit.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ExceptionModel {
    String message;
    Date timestamp;
    Integer status;
    String error;
    String path;
}

