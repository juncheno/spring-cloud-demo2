package com.cj.test.springclouduserservice.demo3.dto;

import com.cj.springcloud.exception.ValidException;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.constraints.NotNull;


@Data
public class AuthLoginDto {

    private String username;

    private String password;

    private String phone;

    private String code;

    private String openId;

    /**
     * @see com.cj.test.springclouduserservice.demo3.enums.LoginTypeEnum
     */
    @NotNull(message = "当前登陆类型不能为空")
    private Integer loginType;

    public void validData(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder stringBuilder=new StringBuilder( );
            for (ObjectError oe: bindingResult.getAllErrors()) {
                stringBuilder.append(oe.getDefaultMessage()+"\n");
            }
            throw new ValidException(stringBuilder.toString());

        }
    }
}
