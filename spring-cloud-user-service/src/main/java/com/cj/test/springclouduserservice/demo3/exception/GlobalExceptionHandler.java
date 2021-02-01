package com.cj.test.springclouduserservice.demo3.exception;


import com.cj.springcloud.api.R;
import com.cj.springcloud.exception.BizException;
import com.cj.springcloud.exception.ValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e, HttpServletRequest request){
      log.info("GlobalException.handleException: {}, {}",request.getRequestURI(),e);
      String msg="系统繁忙:"+e.getMessage();
      System.out.println(msg);
      if(e instanceof ValidException||e instanceof BizException){
          msg=e.getMessage();

      }
      return new R.Build<>().buildCustomize(msg);
    }
}
