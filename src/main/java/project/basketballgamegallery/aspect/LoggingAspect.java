package project.basketballgamegallery.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Aspect for logging execution of service and repository Spring components.
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Pointcut that matches the "getTeamById" method in the TeamController class.
     */
    @Pointcut("execution(* project.basketballgamegallery.controller.TeamController.getTeamById(..))")
    public void getTeamByIDControllerTeams() {
    }

    /**
     * Advice that logs around the "getTeamById" method in the TeamController class.
     *
     * @param pjp the proceeding join point
     * @return the result of the method execution
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("getTeamByIDControllerTeams()")
    public Object logAroundGetTeamById(ProceedingJoinPoint pjp) throws Throwable {
        log.info(" *** Advice Around - (logAroundGetTeamById) is being executed!");
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info(" *** ID value: " + args[i].toString());
        }
        return pjp.proceed(args);
    }

    /**
     * Pointcut that matches all methods in the controllers package.
     */
    @Pointcut("execution(* project.basketballgamegallery.controller.*.*(..))")
    public void controllerMethods() {
    }

    /**
     * Advice that logs before executing a method in the controllers.
     *
     * @param joinPoint the join point at which the advice is applied
     */
    @Before("controllerMethods() && !getTeamByIDControllerTeams()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Before executing method: " + joinPoint.getSignature().getName());
        log.info("Arguments: " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * Advice that logs after executing a method in the controllers.
     *
     * @param joinPoint the join point at which the advice is applied
     */
    @After("controllerMethods() && !getTeamByIDControllerTeams()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("After executing method: " + joinPoint.getSignature().getName());
    }

    /**
     * Advice that logs the execution time of methods in the controllers.
     *
     * @param joinPoint the join point at which the advice is applied
     * @return the result of the method execution
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("controllerMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object proceed = joinPoint.proceed();
        long executionTime = System.nanoTime() - start;
        log.info("Execution time of " + joinPoint.getSignature() + ": " + executionTime + " ns");
        return proceed;
    }

    /**
     * Pointcut that matches all Spring beans annotated with @Repository, @Service, or @RestController.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
    }

    /**
     * Pointcut that matches all beans in the application's main packages.
     */
    @Pointcut("within(project.basketballgamegallery..*)" +
            " || within(project.basketballgamegallery.security..*)" +
            " || within(project.basketballgamegallery.controller..*)")
    public void applicationPackagePointcut() {
    }

    /**
     * Advice that logs after throwing an exception in methods within the application's main packages.
     *
     * @param joinPoint the join point at which the advice is applied
     * @param e         the exception that was thrown
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }

    /**
     * Advice that logs when a method is entered and exited within the application's main packages.
     *
     * @param joinPoint the join point at which the advice is applied
     * @return the result of the method execution
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }
}
