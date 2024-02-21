package moe.imtop1.gdb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import moe.imtop1.gdb.model.dto.system.SysUserDto;
import moe.imtop1.gdb.model.entity.system.SysRole;
import moe.imtop1.gdb.model.entity.system.SysUser;
import moe.imtop1.gdb.model.vo.common.AjaxResult;
import moe.imtop1.gdb.model.vo.common.ResultCodeEnum;
import moe.imtop1.gdb.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anoixa
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "查询用户接口")
    @PostMapping("/findByPage")
    public AjaxResult<IPage<SysUser>> findByPage(@RequestBody SysUserDto sysUserDto) {
        IPage<SysUser> userPage = sysUserService.findByPage(sysUserDto);
        return AjaxResult.build(userPage , ResultCodeEnum.SUCCESS) ;
    }

}
