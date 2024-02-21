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

export const SaveSysRole = queryDto => {
  return request({
    url: `${base_api}/saveSysRole`,
    method: 'post',
    data: queryDto,
  })
}

export const UpdateSysRole = queryDto => {
  return request({
    url: `${base_api}/updateSysRole`,
    method: 'post',
    data: queryDto,
  })
}

export const DeleteSysRole = queryDto => {
  return request({
    url: `${base_api}/deleteSysRole`,
    method: 'post',
    data: {
      id: queryDto.id
    },
  })
}
