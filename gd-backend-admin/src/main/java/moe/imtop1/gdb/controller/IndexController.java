package moe.imtop1.gdb.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import moe.imtop1.gdb.service.SysUserService;
import moe.imtop1.gdb.service.ValidateCodeService;
import moe.imtop1.model.dto.system.LoginDto;
import moe.imtop1.model.entity.system.SysUser;
import moe.imtop1.model.vo.common.Result;
import moe.imtop1.model.vo.common.ResultCodeEnum;
import moe.imtop1.model.vo.system.LoginVo;
import moe.imtop1.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeService validateCodeService;

    @Operation(summary = "登录接口")
    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto) ;
        return Result.build(loginVo, ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "生成图片验证码")
    @GetMapping(value = "/getValidateCode")
    public Result<ValidateCodeVo> getValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.getValidateCode();

        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

    //获取当前登录用户信息
    @GetMapping(value = "/getUserInfo")
    public Result getUserInfo(@RequestHeader(name = "token") String token) {
        //1 从请求头获取token
//        String token = request.getHeader("token");
        //2 根据token查询redis获取用户信息
        SysUser sysUser = sysUserService.getUserInfo(token);

        //3 用户信息返回
        return Result.build(sysUser,ResultCodeEnum.SUCCESS);
    }

}
