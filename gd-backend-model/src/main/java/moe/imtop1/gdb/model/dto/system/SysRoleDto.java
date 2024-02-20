package moe.imtop1.gdb.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import moe.imtop1.gdb.model.dto.h5.BasePageDto;

@Data
@Schema(description = "请求参数实体类")
public class SysRoleDto extends BasePageDto {

    @Schema(description = "角色名称")
    private String roleName ;

}
