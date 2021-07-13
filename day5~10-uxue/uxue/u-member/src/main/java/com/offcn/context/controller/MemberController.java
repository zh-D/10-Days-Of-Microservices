package com.offcn.context.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.alibaba.nacos.api.utils.StringUtils;
import com.offcn.common.utils.JWTUtil;
import com.offcn.common.utils.PageUtils;
import com.offcn.common.utils.R;
import com.offcn.context.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.offcn.context.entity.MemberEntity;


/**
 * 会员-会员表
 *
 * @author sunny
 * @email 1641245614@qq.com
 * @date 2021-07-11 11:03:31
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("context:member:info")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("context:member:save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("context:member:update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("context:member:delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //登录方法
    @PostMapping("login")
    //@RequestBody 接收的是json格式数据
    public R login(@RequestBody MemberEntity memberEntity){
        MemberEntity memberEntityLogin = memberService.login(memberEntity.getUserName(), memberEntity.getPassword());
        //判断登录是否获取到用户信息
        if(memberEntityLogin!=null){
            //使用JWT工具类生成令牌
            String token = JWTUtil.generateToken(memberEntity.getUserName());
            //生成一个随机的key 23sw1-sww21-ee331-sw2222
            String  refreshToken = UUID.randomUUID().toString().replace("-", "");
            //把令牌信息存储到redis，对应value是hash
            stringRedisTemplate.opsForHash().put(refreshToken,"token",token);
            stringRedisTemplate.opsForHash().put(refreshToken,"username",memberEntity.getUserName());

            //redis数据设置有效期，超期数据自动消失
            stringRedisTemplate.expire(refreshToken,JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

            //返回结果
            return R.ok().put("token",token).put("refreshToken",refreshToken);
        }else {
            return R.error("登录失败");
        }
    }

    //刷新令牌
    @RequestMapping("refreshtoken")
    public R refreshToken(String refreshToken){
        //根据refreshToken 去redis读取令牌的用户名
        String username= (String) stringRedisTemplate.opsForHash().get(refreshToken,"username");
        //判断该令牌用户是否存在
        if(StringUtils.isEmpty(username)){
            return R.error("刷新令牌失败");
        }
        //继续刷新令牌
        //重新生成一个令牌
        String token = JWTUtil.generateToken(username);
        //更新保存令牌到redis
        stringRedisTemplate.opsForHash().put(refreshToken,"token",token);
        //重新设置数据有效时间
        stringRedisTemplate.expire(refreshToken,JWTUtil.TOKEN_EXPIRE_TIME,TimeUnit.MILLISECONDS);

        return R.ok().put("token",token).put("refreshToken",refreshToken);


    }


}
