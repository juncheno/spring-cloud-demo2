package com.cj.test.springclouduserservice.demo3.service;

import com.cj.springcloud.api.R;
import com.cj.springcloud.exception.BizException;
import com.cj.test.springclouduserservice.demo3.dto.AuthLoginDto;

public interface LoginService {

    R doLogin(AuthLoginDto authLoginDto) throws BizException;
}
