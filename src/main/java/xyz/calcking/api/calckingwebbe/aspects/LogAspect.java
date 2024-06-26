package xyz.calcking.api.calckingwebbe.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LogAspect {
  @Pointcut("within(xyz.calcking.api.calckingwebbe.controllers..*)" + "||"
      + "@annotation(xyz.calcking.api.calckingwebbe.annotations.Logger)")
  public void pointcut() {
  }

  @Around("pointcut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info(joinPoint.getSignature().toShortString());
    Arrays.stream(joinPoint.getArgs())
        .map(Object::toString)
        .forEach(log::info);

    Object returnValue = joinPoint.proceed();

    if (returnValue != null)
      log.info(returnValue.toString());

    return returnValue;
  }

  @AfterThrowing(pointcut = "pointcut()", throwing = "e")
  public void afterThrowing(JoinPoint joinPoint, Throwable e) {
    log.error(joinPoint.getSignature().toShortString());
    log.error(e.getMessage(), e);
  }
}
