package moe.imtop1.gdb.common.exception;


import lombok.Data;
import moe.imtop1.model.vo.common.ResultCodeEnum;

@Data
public class SystemException extends RuntimeException{

    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public SystemException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
