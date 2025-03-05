package JobApplication.JobApp.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    Logger log= LoggerFactory.getLogger(LoggingAspect.class);
    @Before("execution(* JobApplication.JobApp.Service.JobService.addJob(..))")
    public void beforeAddJob(JoinPoint joinPoint)
    {
        log.info("Before Execution of method "+joinPoint.getSignature().getName());
    }

   // @AfterReturning()
}
