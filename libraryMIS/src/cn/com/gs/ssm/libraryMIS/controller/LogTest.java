package cn.com.gs.ssm.libraryMIS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LogTest {
	private static ApplicationContext ctx;
	@Autowired
	private static ServiceA serviceA;
	/*
	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		ServiceA serviceA = ctx.getBean("serviceA", ServiceA.class);
		serviceA.add();
	}
	*/
	
	public static void main(String[] args) {
		//ctx = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		//ServiceA serviceA = ctx.getBean("serviceA", ServiceA.class);
		serviceA.add();
	}
}