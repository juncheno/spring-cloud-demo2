package com.cj.test.springclouduserservice.demo3.hystrix;


import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

@Component
@Aspect
public class CjHystrixCommandAspect {

    ExecutorService executorService=Executors.newFixedThreadPool(10);


    @Pointcut("@annotation(CjHystrixCommand)")
    public void pointcut(){

    }
    @Around(value = "pointcut()&&@annotation(hystrixCommand)")
    public Object doPointcut(ProceedingJoinPoint proceedingJoinPoint,CjHystrixCommand hystrixCommand) throws InterruptedException, ExecutionException, TimeoutException {
        System.out.println("---------");
        Object result = null;
        int timeout=hystrixCommand.timeout();
        Future future=executorService.submit(()->{
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });

        try {
            System.out.println("++++++++++++++++++++++++++++++++");
            result=future.get(timeout,TimeUnit.MILLISECONDS);
            System.out.println("*********result"+result+"*****timeout"+timeout);
        } catch (InterruptedException|ExecutionException|TimeoutException e) {
            System.out.println("###################11"+result);
            e.printStackTrace();
            future.cancel(true);
            if(StringUtils.isBlank(hystrixCommand.fallback())){
                throw e;
            }
            result=invokefallback(proceedingJoinPoint,hystrixCommand.fallback());

        }
        //降级处理
        if(result==null)
            result=invokefallback(proceedingJoinPoint,hystrixCommand.fallback());


        System.out.println("###################"+result);
        return result;
    }


        public Object invokefallback(ProceedingJoinPoint joinPoint, String fallback){

            MethodSignature signature= (MethodSignature) joinPoint.getSignature();
            Method method=signature.getMethod();
            Class<?>[] parameterTypes=method.getParameterTypes();
            Method fallbackMethod=null;

            try {
                fallbackMethod=joinPoint.getTarget().getClass().getMethod(fallback,parameterTypes);
                fallbackMethod.setAccessible(true);
                return fallbackMethod.invoke(joinPoint.getTarget(),joinPoint.getArgs());
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

}
