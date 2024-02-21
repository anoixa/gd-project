package moe.imtop1.gdb.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import moe.imtop1.gdb.model.dto.h5.BasePageDto;

@Data
public class SysRoleDto extends BasePageDto {
    private String id;
    private String roleName;
    private String roleCode;
    private String description;
}
