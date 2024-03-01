package bank.aop;

import bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class RepositoryLoggingAdvice {

    @Autowired
    private Logger logger;

    @Pointcut("execution(* bank.repository.*.*(..))")
    public void repositoryPointcut(){}

    @After("repositoryPointcut()")
    public void repositoryLogging(JoinPoint joinPoint){
        logger.log("Repository Method Call -- Method: "+ joinPoint.getSignature().getName()+
                " was called on pointcut: "+joinPoint.getTarget().getClass());
    }
}
