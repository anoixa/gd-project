package moe.imtop1.gdb.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import moe.imtop1.gdb.service.SysUserService;
import moe.imtop1.gdb.service.ValidateCodeService;
import moe.imtop1.gdb.utils.AuthContextUtil;
import moe.imtop1.gdb.model.dto.system.LoginDto;
import moe.imtop1.gdb.model.entity.system.SysUser;
import moe.imtop1.gdb.model.vo.common.Result;
import moe.imtop1.gdb.model.vo.common.ResultCodeEnum;
import moe.imtop1.gdb.model.vo.system.LoginVo;
import moe.imtop1.gdb.model.vo.system.ValidateCodeVo;
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

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping(value = "/getUserInfo")
    public Result<SysUser> getUserInfo() {
        return Result.build(AuthContextUtil.get()  , ResultCodeEnum.SUCCESS) ;
    }


    @Operation(summary = "用户退出")
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.logout(token);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

}
