package cn.com.gs.ssm.libraryMIS.controller.tool;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/tool/base64")
public class Base64Controller {
	
	@RequiresPermissions("base64:toBase64")
	@RequestMapping(value = "toBase64")
	public String toBase64() {
		return "base64/base64";
	}

	@RequiresPermissions("base64:toBase64Encode")
	@RequestMapping(value = "toBase64Encode")
	public String toBase64Encode() {
		return "base64/base64Encode";
	}
	
	@RequiresPermissions("base64:toBase64Decode")
	@RequestMapping(value = "toBase64Decode")
	public String base64Encode() {
		return "base64/base64Decode";
	}

}
