package com.cj.test.springclouduserservice.demo3.service.impl;

import com.cj.springcloud.api.R;
import com.cj.springcloud.exception.BizException;
import com.cj.test.springclouduserservice.demo3.dto.AuthLoginDto;
import com.cj.test.springclouduserservice.demo3.mapper.entitys.TbMember;
import com.cj.test.springclouduserservice.demo3.service.LoginService;
import com.cj.test.springclouduserservice.demo3.utils.JwtGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public abstract class AbstractLoginService implements LoginService {
    public static ConcurrentHashMap<Integer,AbstractLoginService> loginMap
    =new ConcurrentHashMap();

    @PostConstruct
    public void init(){
        loginMap.put(getLoginType(),this);
    }

    @Override
    public R doLogin(AuthLoginDto authLoginDto) throws BizException {

        log.info("AbstrctLoginService.doLogin: "+authLoginDto);
        validate(authLoginDto);

        TbMember member=doPocessor(authLoginDto);
        Map payload=new HashMap();
        payload.put("uid",member.getId());
        payload.put("exp",DateTime.now().plusHours(1).toDate().getTime()/1000);
        String token=JwtGeneratorUtil.generatorToken(payload);
        return new R.Build<>().setData(token).buildOk();
    }


    /**
     * 子类圣母登陆类型
     */
    public abstract int getLoginType();

    /**
     * 子类去完成验证
     * @param authLoginDto
     */

    public abstract void validate(AuthLoginDto authLoginDto);


    /**
     * 登陆检验
     * @param authLoginDto
     */
    public abstract TbMember doPocessor(AuthLoginDto authLoginDto);
}
