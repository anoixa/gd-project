package moe.imtop1.gdb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.imtop1.gdb.mapper.SysRoleMapper;
import moe.imtop1.gdb.model.dto.system.SysRoleDto;
import moe.imtop1.gdb.model.entity.system.SysRole;
import moe.imtop1.gdb.model.entity.system.SysUser;
import moe.imtop1.gdb.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author anoixa
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public IPage<SysRole> findByPage(SysRoleDto sysRoleDto) {
        Page<SysRole> pageInfo = new Page<>(sysRoleDto.getPageNum(), sysRoleDto.getPageSize());

        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!StringUtils.hasLength(sysRoleDto.getRoleName()), SysRole::getRoleName, sysRoleDto.getRoleName())
                .eq(SysRole::getIsDeleted, 0)
                .orderByAsc(SysRole::getRoleName);

        return sysRoleMapper.selectPage(pageInfo, lambdaQueryWrapper);
    }

    @Override
    @Transactional
    public void saveSysRole(SysRoleDto sysRoleDto) {
        SysRole sysRole = new SysRole();

        sysRole.setRoleName(sysRoleDto.getRoleName());
        sysRole.setRoleCode(sysRoleDto.getRoleCode());
        sysRole.setDescription(sysRoleDto.getDescription());
        sysRole.setIsDeleted(0);

        sysRoleMapper.insert(sysRole);
    }

    @Override
    @Transactional
    public void updateSysRole(SysRoleDto sysRoleDto) {
        LambdaUpdateWrapper<SysRole> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(SysRole::getId, sysRoleDto.getId())
                .set(!StringUtils.hasLength(sysRoleDto.getRoleName()), SysRole::getRoleName, sysRoleDto.getRoleName())
                .set(!StringUtils.hasLength(sysRoleDto.getRoleCode()), SysRole::getRoleCode, sysRoleDto.getRoleCode())
                .set(!StringUtils.hasLength(sysRoleDto.getDescription()), SysRole::getDescription, sysRoleDto.getDescription());

        sysRoleMapper.update(lambdaUpdateWrapper);
    }

    @Override
    @Transactional
    public void deleteSysRole(SysRoleDto sysRoleDto) {
        LambdaUpdateWrapper<SysRole> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(SysRole::getId, sysRoleDto.getId())
                .set(SysRole::getIsDeleted, 1);

        sysRoleMapper.update(lambdaUpdateWrapper);

    }
}
