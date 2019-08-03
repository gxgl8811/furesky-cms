package com.furesky.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.furesky.base.ActionResult;




/**
 * 日志切面类
 * 
 * @author jianda
 * @date 2018年2月5日
 */
// 声明切面
@Aspect
// 注入依赖
@Component
public class LogAspect {

	// 声明切入点
	@Pointcut("@annotation(com.iasy.log.Log)")
	public void pointcut() {

	}

	// 前置通知
	// 在目标方法之前，执行
	@Before("pointcut()")
	public void before(JoinPoint point) {
		Object[] params = point.getArgs();
		for (Object obj : params) {
			System.out.println(obj);
		}
	}

	// 后置通知
	// 在目标方法之后，执行
	@After("pointcut()")
	public void after(JoinPoint point) {
		Object[] params = point.getArgs();
		for (Object obj : params) {
			System.out.println(obj);
		}
	}

	// 返回通知
	// 目标方法正常结束后，执行
	@AfterReturning(value = "pointcut()", returning = "result")
	public Object afterReturning(JoinPoint point, Object result) {
		return result;
	}

	// 异常通知
	// 目标方法出现异常时，执行
	@AfterThrowing(value = "pointcut()", throwing = "e")
	public void afterthrowing(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println(methodName + " occurs excetion: " + ex);
	}

	// 环绕通知
	// 环绕目标方法
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object[] params = point.getArgs();
		for (Object obj : params) {
			System.out.println(obj);
		}
		ActionResult aResult = ActionResult.getSuccess();
		if (aResult.getSuccessful()) {
			return point.proceed();
		}
		return aResult;
	}
}
