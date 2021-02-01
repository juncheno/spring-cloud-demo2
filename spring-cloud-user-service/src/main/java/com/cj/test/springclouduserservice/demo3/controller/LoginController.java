package com.cj.test.springclouduserservice.demo3.controller;


import com.cj.springcloud.api.R;
import com.cj.springcloud.exception.BizException;
import com.cj.test.springclouduserservice.demo3.dto.AuthLoginDto;
import com.cj.test.springclouduserservice.demo3.service.LoginService;
import com.cj.test.springclouduserservice.demo3.service.impl.AbstractLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {


    @PostMapping("/login")
    public R loginAuth(@RequestBody @Validated AuthLoginDto authLoginDto, BindingResult result){
        authLoginDto.validData(result);

        LoginService loginService=AbstractLoginService.loginMap.get(authLoginDto.getLoginType());

        if(loginService==null){
            throw new BizException("不支持该登陆类型");
        }

        return loginService.doLogin(authLoginDto);




    }
}
