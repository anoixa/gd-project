package moe.imtop1.gdb.service.impl;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.imtop1.gdb.common.exception.SystemException;
import moe.imtop1.gdb.mapper.SysUserMapper;
import moe.imtop1.gdb.service.SysUserService;
import moe.imtop1.gdb.model.dto.system.LoginDto;
import moe.imtop1.gdb.model.entity.system.SysUser;
import moe.imtop1.gdb.model.vo.common.ResultCodeEnum;
import moe.imtop1.gdb.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 登录接口
 * @author anoixa
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper ;
    @Autowired
    private StringRedisTemplate redisTemplate ;

    @Override
    public LoginVo login(LoginDto loginDto) {
        // 根据用户名查询用户
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getUserName, loginDto.getUserName());
        SysUser sysUser = sysUserMapper.selectOne(lambdaQueryWrapper);
        if(sysUser == null) {
            throw new SystemException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 验证密码是否正确
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes()).toLowerCase();
        if(!md5InputPassword.equals(sysUser.getPassword().toLowerCase())) {
            throw new SystemException(ResultCodeEnum.LOGIN_ERROR) ;
        }

        //验证校验码
        String captcha = loginDto.getCaptcha().toLowerCase();
        String codeKey = loginDto.getCodeKey();

        String redisCode = redisTemplate.opsForValue().get("user:validate" + codeKey);
        if (!StringUtils.hasLength(redisCode) || !captcha.equals(redisCode.toLowerCase())) {
            throw new SystemException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        // 生成令牌，保存数据到Redis中
        String token = UUID.randomUUID().toString().replace("-", "");

        redisTemplate.opsForValue().set("user:login:" + token
                , JSON.toJSONString(sysUser)
                , 30
                , TimeUnit.MINUTES);
//        redisCache.setCacheObject("loginUser:" + token
//                , JSON.toJSONString(sysUser)
//                , 30
//                , TimeUnit.MINUTES);

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo() ;
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        // 返回
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String redisCode = redisTemplate.opsForValue().get("user:login:" + token);
        SysUser sysUser = JSON.parseObject(redisCode, SysUser.class);

        return sysUser;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login" + token);
    }
}
