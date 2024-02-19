package moe.imtop1.gdb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import moe.imtop1.model.dto.system.LoginDto;
import moe.imtop1.model.entity.system.SysUser;
import moe.imtop1.model.vo.system.LoginVo;

public interface SysUserService extends IService<SysUser> {
    LoginVo login(LoginDto loginDto);
    SysUser getUserInfo(String token);
}
