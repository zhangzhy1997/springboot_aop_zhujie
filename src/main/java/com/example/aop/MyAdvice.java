package com.example.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect // 表示该类是一个通知类
@Component // 交给spring管理
public class MyAdvice {
    /**
     * 前置通知
     */
    @Before("execution(* com.example.service.UserService.save(..))")
    public void before(JoinPoint joinPoint) throws Exception{
        System.out.println("---------------前置通知开始(注解)~~~~~~~~~~~");
        // 获取到类名
        String targetName = joinPoint.getTarget().getClass().getName();
        System.out.println("代理的类是:" + targetName);
        // 获取到方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println("增强的方法是:" + methodName);
        // 获取到参数
        Object[] parameter = joinPoint.getArgs();
        System.out.println("传入的参数是:" + Arrays.toString(parameter));
        // 获取字节码对象
        Class<?> targetClass = Class.forName(targetName);
        // 获取所有的方法
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == parameter.length) {
                    System.out.println("找到这个方法");
                    break;
                }
            }
        }
        System.out.println("---------------前置通知结束(注解)~~~~~~~~~~~");
    }

    /**
     * 后置通知
     * returnVal,切点方法执行后的返回值
     */
    @AfterReturning(value="execution(* com.example.service.UserService.save(..))",returning = "returnVal")
    public void afterReturning(Object returnVal){
        System.out.println("后置通知...."+returnVal);
    }


    /**
     * 环绕通知
     * @param pjp 可用于执行切点的类
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.example.service.UserService.save(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("----------------环绕通知之前 的部分(注解)----------------");
        // 获取到类名
        String targetName = pjp.getTarget().getClass().getName();
        System.out.println("代理的类是:" + targetName);
        // 获取到参数
        Object[] parameter = pjp.getArgs();
        System.out.println("传入的参数是:" + Arrays.toString(parameter));
        // 获取到方法签名，进而获得方法
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        System.out.println("增强的方法名字是:" + method.getName());
        // 获取参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        System.out.println("参数类型是:" + parameterTypes.toString());

        //让方法执行(proceed是拦截的方法的执行返回值，可以针对返回值做一些处理)
        System.out.println("--------------方法开始执行-----------------");
        Object proceed = pjp.proceed();

        //环绕通知之后的业务逻辑部分
        System.out.println("----------------环绕通知之后的部分(注解)----------------");
        return proceed;
    }

    /**
     * 抛出通知
     * @param e
     */
    @AfterThrowing(value="execution(* com.example.service.UserService.save(..))",throwing = "e")
    public void afterThrowable(Throwable e){
        System.out.println("出现异常:msg="+e.getMessage());
    }

    /**
     * 无论什么情况下都会执行的方法
     */
    @After(value="execution(* com.example.service.UserService.save(..))")
    public void after(){
        System.out.println("最终通知....");
    }
}
