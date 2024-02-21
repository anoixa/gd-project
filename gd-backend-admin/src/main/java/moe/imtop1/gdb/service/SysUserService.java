package moe.imtop1.gdb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import moe.imtop1.gdb.model.dto.system.LoginDto;
import moe.imtop1.gdb.model.dto.system.SysUserDto;
import moe.imtop1.gdb.model.entity.system.SysRole;
import moe.imtop1.gdb.model.entity.system.SysUser;
import moe.imtop1.gdb.model.vo.system.LoginVo;

public interface SysUserService extends IService<SysUser> {
    LoginVo login(LoginDto loginDto);
    SysUser getUserInfo(String token);
    void logout(String token);

    IPage<SysUser> findByPage(SysUserDto sysUserDto);
}
