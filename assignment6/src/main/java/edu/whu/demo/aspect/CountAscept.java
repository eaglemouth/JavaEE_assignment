package edu.whu.demo.aspect;

import lombok.Getter;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
@Component
@Aspect
public class CountAscept {

    @Getter
    public ConcurrentHashMap<String, ApiStatistics> statisticsMap = new ConcurrentHashMap<>();

    @Pointcut("execution(* edu.whu.demo.controller.*.*(..))")
    public void controllerPointCut(){}

    @Around("controllerPointCut()")
    public Object apiMonitor(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        ApiStatistics stats = statisticsMap.computeIfAbsent(pjp.getSignature().toShortString(), k -> new ApiStatistics());

        try {
            Object result = pjp.proceed();
            long end = System.currentTimeMillis();
            long responseTime = end - start;
            stats.setCallCount(stats.getCallCount() + 1);
            stats.setTotalResponseTime(stats.getTotalResponseTime() + responseTime);
            stats.setMinResponseTime(Math.min(stats.getMinResponseTime(), responseTime));
            stats.setMaxResponseTime(Math.max(stats.getMaxResponseTime(), responseTime));
            return result;
        } catch (Throwable e) {
            stats.setErrorCount(stats.getErrorCount() + 1);
            throw e;
        }
    }

}
