package com.atguigu.lease.common.exception;

import com.atguigu.lease.common.result.ResultCodeEnum;
import lombok.Data;

@Data
public class LeaseException extends RuntimeException {
    private Integer code;

    public LeaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public LeaseException(ResultCodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }
}
