package com.demo.app.annotation;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExternalLoggingAspect {

    @Around("@annotation(com.demo.app.annotation.ExternalCommunication)")
    public Object inspect(ProceedingJoinPoint pjp) throws Throwable {
        if(null==pjp){
            return new Object();
        }

        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        String request=mapper.writeValueAsString(array);
        Object object = pjp.proceed();
        String response=mapper.writeValueAsString(object);

        buildAndLog(methodName,className,request,response);
        return object;

    }

    private void buildAndLog(String methodName, String className, String request, String response) {
        StringBuilder sb=new StringBuilder();
        sb.append("className: ").append(className)
                .append("\tMethod Name: ").append(methodName)
                .append("\tRequest: ").append(request)
                .append("\tResponse: ").append(response);
        log.info(sb.toString());
    }

}