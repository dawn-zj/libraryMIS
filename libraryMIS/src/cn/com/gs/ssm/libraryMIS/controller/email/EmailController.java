package cn.com.gs.ssm.libraryMIS.controller.email;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.gs.ssm.libraryMIS.service.IEmailService;

@Controller
@RequestMapping("/email")
public class EmailController {
	@Autowired
	public IEmailService emailService;
	
	@RequestMapping("emailManage")
	public String emailManage(){
		return "email/email";
	}
	
	@RequestMapping("sendEmail")
//	@ResponseBody
	public void sendEmail(String receiveMailAccount,String subject,String content,
			HttpServletResponse response){
		HashMap resultMap = new HashMap<>();
		try {
			emailService.sendEmail(receiveMailAccount, subject, content);
//			resultMap.put("success", true);
//			resultMap.put("message", "发送成功");
			response.getWriter().print("ok");
		} catch (Exception e) {
//			resultMap.put("success", false);
//			resultMap.put("message", "发送失败");
		}
	}
}
