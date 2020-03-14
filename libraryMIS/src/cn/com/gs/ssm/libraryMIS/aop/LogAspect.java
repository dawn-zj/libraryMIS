package cn.com.gs.ssm.libraryMIS.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {
	/*
	public void doAround() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		System.out.println(date+"  做了一笔业务");
	}*/
	
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		//在ProceedingJoinPoint对象中获取各种信息，记录日志
		String targetName = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		System.out.println(date + "  访问了" + targetName + "." + methodName + "方法");
		
		//将参数继续向下传递
		Object result = pjp.proceed();
		return result;
	}
	
}
