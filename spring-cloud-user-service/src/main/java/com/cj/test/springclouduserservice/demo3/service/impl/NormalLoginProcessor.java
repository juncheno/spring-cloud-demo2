package com.cj.test.springclouduserservice.demo3.service.impl;

import com.cj.springcloud.exception.BizException;
import com.cj.springcloud.exception.ValidException;
import com.cj.test.springclouduserservice.demo3.dto.AuthLoginDto;
import com.cj.test.springclouduserservice.demo3.enums.LoginTypeEnum;
import com.cj.test.springclouduserservice.demo3.mapper.entitys.TbMember;
import com.cj.test.springclouduserservice.demo3.mapper.entitys.TbMemberExample;
import com.cj.test.springclouduserservice.demo3.mapper.persistence.TbMemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;


@Slf4j
@Service
public class NormalLoginProcessor extends AbstractLoginService{

    @Autowired
    private TbMemberMapper memberMapper;

    @Override
    public int getLoginType() {
        return LoginTypeEnum.NORMAL.getCode();
    }

    @Override
    public void validate(AuthLoginDto authLoginDto) {
        if(StringUtils.isBlank(authLoginDto.getUsername())||StringUtils.isBlank(authLoginDto.getPassword())){
            throw new ValidException("账号密码不能为空");
        }

    }

    @Override
    public TbMember doPocessor(AuthLoginDto authLoginDto) {
        log.info("NormalLoginProcessor.doPocessor"+authLoginDto);
        TbMemberExample example=new TbMemberExample();
        example.createCriteria().andStateEqualTo(1).andUsernameEqualTo(authLoginDto.getUsername());

        List<TbMember> memberList=memberMapper.selectByExample(example);
        if(memberList==null||memberList.size()==0){
            throw new BizException("不存在用户"+authLoginDto.getUsername());
        }

        if(!DigestUtils.md5DigestAsHex(authLoginDto.getPassword().getBytes()).equals(memberList.get(0).getPassword())){
            throw new BizException("用户名或密码错误");
        }
        return memberList.get(0);

    }
}
