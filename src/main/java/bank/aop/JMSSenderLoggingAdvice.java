package bank.aop;

import bank.integration.jms.JMSSenderImpl;
import bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class JMSSenderLoggingAdvice {

    @Autowired
    private Logger logger;

//    @After("execution(* bank.JMSSender.*.*(..)) && args (message))")
    @After("execution(* bank.integration.jms.*.*(..)) && args (message))")
    public void JMSSenderLogging(JoinPoint joinPoint, String message){
        logger.log("Sent message: "+ message);
    }
}
