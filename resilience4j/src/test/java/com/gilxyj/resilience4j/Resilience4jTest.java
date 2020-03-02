package com.gilxyj.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-03-01 22:21
 **/
public class Resilience4jTest {

    @Test
    public void test01(){
        //获取一个CircuitBreakerRegistry实例，可以调用ofDefaults获取一个CircuitBreakerRegistry实例，也可以自定义属性。
        //CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                //故障率阈值百分比，超过这个阈值，断路器就会打开
                .failureRateThreshold(50)
                //断路器保持打开的时间，在到达设置的时间之后，断路器会进入到half open的状态
                .waitDurationInOpenState(Duration.ofMillis(1000))
                //当断路器处于half open状态时，环形缓冲区的大小
                .permittedNumberOfCallsInHalfOpenState(2)
                .slidingWindowSize(2)
                .build();
        CircuitBreakerRegistry r1 = CircuitBreakerRegistry.of(config);
        CircuitBreaker cb1 = r1.circuitBreaker("gilxyj1");
        CircuitBreaker cb2 = r1.circuitBreaker("gilxyj1", config);
        CheckedFunction0<String> supplier = CircuitBreaker.decorateCheckedSupplier(cb1, () -> "hello Resilience4j");
        Try<String> result = Try.of(supplier)
                .map(v -> v + " hello world");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    @Test
    public void test02(){
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                //故障率阈值百分比，超过这个阈值，断路器就会打开
                .failureRateThreshold(50)
                //断路器保持打开的时间，在到达设置的时间之后，断路器会进入到half open的状态
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowSize(2)
                .build();
        CircuitBreakerRegistry r1 = CircuitBreakerRegistry.of(config);
        CircuitBreaker cb1 = r1.circuitBreaker("gilxyj1");
        //获取断路器的状态
        System.out.println(cb1.getState());

        cb1.onError(0, TimeUnit.SECONDS,new RuntimeException() );
        System.out.println(cb1.getState());

        cb1.onError(0, TimeUnit.SECONDS,new RuntimeException() );
        System.out.println(cb1.getState());

        CheckedFunction0<String> supplier = CircuitBreaker.decorateCheckedSupplier(cb1, () -> "hello Resilience4j");
        Try<String> result = Try.of(supplier)
                .map(v -> v + " hello world");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    @Test
    public void test03() throws ParseException {
        //表示从应用启动后，每3秒至多允许1个请求，获取授权操作最多可等待2s，3秒内超过1个请求，会抛出RequestNotPermitted异常，需等待下一个3s周期，重新刷新1000个授权。
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(3000))
                .limitForPeriod(1)
                .timeoutDuration(Duration.ofMillis(2000))
                .build();
        RateLimiter rateLimiter = RateLimiter.of("gilxyj", config);
        CheckedRunnable checkedRunnable = RateLimiter.decorateCheckedRunnable(rateLimiter, () -> {
            System.out.println(new Date());
        });
        Try.run(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable)
                .onFailure(t-> System.out.println(t));

    }

    @Test
    public void test04(){
        RetryConfig config = RetryConfig.custom()
                //重试次数
                .maxAttempts(5)
                //重试间隔
                .waitDuration(Duration.ofMillis(500))
                //重试异常
                .retryExceptions(RuntimeException.class)
                .build();

        Retry retry = Retry.of("gilxyj", config);
        Retry.decorateRunnable(retry, new Runnable() {

            int count =0;
            //开启了重试功能之后，run方法执行时，如果抛出RuntimeException异常，会自动触发重试功能
            @Override
            public void run() {
                if(count++<3){
                    System.out.println(count);
                    throw new RuntimeException();
                }
            }
        }).run();
    }

}
