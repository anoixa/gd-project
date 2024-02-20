import request from '@/utils/request'

const base_api = '/admin/system/sysRole'

// 分页查询角色数据
export const GetSysRoleListByPage = queryDto => {
  return request({
    url: `${base_api}/findByPage`,
    method: 'post',
    data: queryDto,
  })
}
