package cn.com.gs.ssm.libraryMIS.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/nav")
public class NavController {
	@RequestMapping("/localRefresh.do")
	public ModelAndView localRefresh(HttpServletRequest request, HttpServletResponse response,ModelAndView mv){
		String classValue = request.getParameter("classValue");
		System.out.println(classValue);
		if(classValue.equals("1")){
			try {
				mv.setViewName("li1");
				/*response.getWriter().print(mv);*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mv;
	}
}
