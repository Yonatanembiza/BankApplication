package bank.aop;

import bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class ServiceLoggingAdvice {
    @Autowired
    private Logger logger;

    @Around("execution(* bank.service.*.*(..))")
    public Object serviceLogging(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch sw = new StopWatch();
        sw.start(joinPoint.getSignature().getName());
        Object returnValue = joinPoint.proceed();
        sw.stop();

        long totalElapsed = sw.getTotalTimeMillis();
        System.out.println("Total time it takes to execute method "+
                joinPoint.getSignature().getName() + " " +
                totalElapsed+" in msec");
        logger.log("Time toexecute method, "+
                joinPoint.getSignature().getName()+
                " is "+ totalElapsed+ " msec");

        return returnValue;
    }
}
