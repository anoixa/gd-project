package moe.imtop1.gdb.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import moe.imtop1.gdb.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author anoixa
 */
@Repository
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

}
