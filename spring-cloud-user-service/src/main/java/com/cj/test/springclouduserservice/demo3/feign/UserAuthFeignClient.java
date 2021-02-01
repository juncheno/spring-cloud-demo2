package com.cj.test.springclouduserservice.demo3.feign;

import com.cj.springcloud.api.R;
import com.cj.springcloud.exception.ValidException;
import com.cj.test.springclouduserservice.demo3.utils.JwtGeneratorUtil;
import com.test.cj.examples.IuserAuthFeignClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthFeignClient  implements IuserAuthFeignClient {
    @Override
    public R<String> validToken(String token) {
        if(StringUtils.isBlank(token)){
            throw new ValidException("token为空");
        }
        try {

            Claims claims=JwtGeneratorUtil.parseToken(token);
            return new R.Build<>().setData(claims.get("uid").toString()).buildOk();
        }catch (ExpiredJwtException e){
            return new R.Build<>().buildCustomize("token已过去");

        }catch (SignatureException e){
            return new R.Build<>().buildCustomize("签名校验失败");

        }

    }
}
