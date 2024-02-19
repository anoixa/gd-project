package moe.imtop1.gdb.common.exception;

import lombok.extern.slf4j.Slf4j;
import moe.imtop1.model.vo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * @author anoixa
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        log.error(e.getMessage());
        return Result.build(500,"未知错误") ;
    }

    //自定义异常处理
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public Result error(SystemException e) {
        return Result.build(e.getResultCodeEnum());
    }

}
