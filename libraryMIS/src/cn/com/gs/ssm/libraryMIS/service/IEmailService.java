package cn.com.gs.ssm.libraryMIS.service;

public interface IEmailService {
	public void sendEmail(String receiveMailAccount,String subject,String content) throws Exception;
}
