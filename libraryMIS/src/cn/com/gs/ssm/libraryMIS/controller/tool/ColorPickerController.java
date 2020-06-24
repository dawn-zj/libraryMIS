package cn.com.gs.ssm.libraryMIS.controller.tool;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/tool/colorpicker")
public class ColorPickerController {
	

	@RequestMapping(value = "toColorpicker")
	public String toBase64Encode() {
		return "colorpicker/colorpicker";
	}


}
