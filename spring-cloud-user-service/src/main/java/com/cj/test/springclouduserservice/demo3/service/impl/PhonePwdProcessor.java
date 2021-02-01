package com.cj.test.springclouduserservice.demo3.service.impl;

import com.cj.test.springclouduserservice.demo3.dto.AuthLoginDto;
import com.cj.test.springclouduserservice.demo3.enums.LoginTypeEnum;
import com.cj.test.springclouduserservice.demo3.mapper.entitys.TbMember;
import org.springframework.stereotype.Service;


@Service
public class PhonePwdProcessor extends AbstractLoginService {
    @Override
    public int getLoginType() {
        return LoginTypeEnum.PHONE.getCode();
    }

    @Override
    public void validate(AuthLoginDto authLoginDto) {

    }

    @Override
    public TbMember doPocessor(AuthLoginDto authLoginDto) {
        return null;

    }
}
