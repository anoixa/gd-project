package moe.imtop1.gdb.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import moe.imtop1.gdb.model.entity.base.BaseEntity;
import lombok.Data;

@Data
@TableName("sys_user_role")
public class SysRoleUser extends BaseEntity {

    private Long roleId;
    private Long userId;

}
