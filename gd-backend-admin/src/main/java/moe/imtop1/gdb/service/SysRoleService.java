package moe.imtop1.gdb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import moe.imtop1.gdb.model.dto.system.SysRoleDto;
import moe.imtop1.gdb.model.entity.system.SysRole;


/**
 * @author anoixa
 */
public interface SysRoleService {
    IPage<SysRole> findByPage(SysRoleDto sysRoleDto);
    void saveSysRole(SysRoleDto sysRoleDto);
    void updateSysRole(SysRoleDto sysRoleDto);
    void deleteSysRole(SysRoleDto sysRoleDto);
}
