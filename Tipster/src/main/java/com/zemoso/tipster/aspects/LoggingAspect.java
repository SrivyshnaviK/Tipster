package com.zemoso.tipster.aspects;

import com.zemoso.tipster.entity.User;
import com.zemoso.tipster.entity.UserCredential;
import com.zemoso.tipster.service.UserCredentialService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private UserCredentialService userCredentialService;
    public LoggingAspect(UserCredentialService userCredentialService){
        this.userCredentialService=userCredentialService;
    }
    @After("execution(public String saveUser(..))")
    public void addToCredentials(JoinPoint joinPoint){
        Object[] args=joinPoint.getArgs();
        User user= (User) args[0];
        UserCredential userCredential =new UserCredential(user.getEmail(),user.getPassword());
        userCredential.setUser(user);
        userCredentialService.save(userCredential);
    }
}
