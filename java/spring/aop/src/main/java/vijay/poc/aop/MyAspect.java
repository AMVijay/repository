package vijay.poc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
	
	/**
	 * Method to log before execution.
	 * @param joinPoint
	 */
	@Before("execution(* vijay.poc.aop.web.HelloController.index(..))")
	public void aspectBeforeMethod(JoinPoint joinPoint) {
		System.out.println("in aspectBeforeMethod");
	}
	
	
	/**
	 * Method to log after execution.
	 * @param joinPoint
	 */
	@After("execution(* vijay.poc.aop.web.HelloController.index(..))")
	public void aspectAfterMethod(JoinPoint joinPoint) {
		System.out.println("in aspectAfterMethod");
		System.out.println("Signature Name :: " + joinPoint.getSignature().getName());
	}
	
	/**
	 * Method to log return value.
	 * @param joinPoint as JoinPoint
	 * @param result as Object
	 */
	@AfterReturning(pointcut="execution(* vijay.poc.aop.web.HelloController.index(..))", returning="result")
	public void aspetAfterReturningMethod(JoinPoint joinPoint, Object result) {
		System.out.println("logAfterReturning() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("Method returned value is : " + ((ResponseEntity<String>)result).getBody());
		System.out.println("******");

	}
}
