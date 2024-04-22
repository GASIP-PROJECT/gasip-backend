package com.example.gasip.global.redisson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RedissonLockAspect {

    private final RedissonClient redissonClient;

    @Around("@annotation(com.example.gasip.global.redisson.RedissonLock)")
    public void redissonLock(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedissonLock annotation = method.getAnnotation(RedissonLock.class);
        String lockKey = method.getName() + CustomSpringELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), annotation.value());


//        final String lockName = "postId:lock";
        final RLock lock = redissonClient.getLock(lockKey);

        try {
            boolean locakable = lock.tryLock(1, 3, TimeUnit.SECONDS);
            if(!locakable){
                return;
            }
            joinPoint.proceed();

        } catch (InterruptedException e) {
            throw e;
        } finally {
            if(lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
    }
}
