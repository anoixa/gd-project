package moe.imtop1.gdb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import moe.imtop1.gdb.model.dto.system.SysRoleDto;
import moe.imtop1.gdb.model.entity.system.SysRole;
import moe.imtop1.gdb.model.vo.common.AjaxResult;
import moe.imtop1.gdb.model.vo.common.ResultCodeEnum;
import moe.imtop1.gdb.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author anoixa
 */
@Tag(name = "角色接口")
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService ;

    @Operation(summary = "查询角色接口")
    @PostMapping("/findByPage")
    public AjaxResult<IPage<SysRole>> findByPage(@RequestBody SysRoleDto sysRoleDto) {
        IPage<SysRole> byPage = sysRoleService.findByPage(sysRoleDto);
        return AjaxResult.build(byPage , ResultCodeEnum.SUCCESS) ;
    }

}