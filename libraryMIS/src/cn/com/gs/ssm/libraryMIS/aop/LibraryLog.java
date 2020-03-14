package cn.com.gs.ssm.libraryMIS.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LibraryLog {

	/** 要执行的操作类型比如：add操作 **/
	public String optype() default "";
}
