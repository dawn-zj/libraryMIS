package cn.com.gs.ssm.libraryMIS.controller.log;

import cn.com.gs.common.define.Constants;
import cn.com.gs.common.util.FileUtil;
import cn.com.gs.common.util.StringUtil;
import cn.com.gs.common.util.logger.LoggerFileUtil;
import cn.com.gs.ssm.libraryMIS.controller.BaseController;
import cn.com.gs.ssm.libraryMIS.model.vo.OperateLogVO;
import cn.com.gs.ssm.libraryMIS.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/log")
public class LogController extends BaseController{
	@Autowired(required=false)
	private HttpServletRequest request;

	@RequestMapping("/errorLogList")
	public String errorLogList(Page<OperateLogVO> page){
		File[] fs = LoggerFileUtil.logFile("error");
		int total = fs.length;
		int start = page.getStart();
		int end = page.getEnd();
		List<OperateLogVO> list = new ArrayList<OperateLogVO>();
		for (int i = 1; i <= fs.length; i++) {
			File f = fs[i - 1];
			if (f.isFile() && i >= start && i <= end) {
				OperateLogVO log = new OperateLogVO();
				log.setFileName(f.getName());
				log.setFilePath(f.getAbsolutePath());
				log.setFileSize(f.length() / 1024);
				log.setFileTime(f.lastModified());
				list.add(log);
			}
		}
		page.setTotalNo(total);
		page.setResult(list);

		request.setAttribute("page", page);
		return "log/errorLogList";
	}

	@RequestMapping("/debugLogList")
	public String debugLogList(){
		return "log/debugLogList";
	}

	@RequestMapping(value = "downloadFile")
	public void downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (fileName == null || "".equals(fileName))
			throw new Exception("文件名称为空");
		if (!Pattern.matches("[0-9A-Za-z.]+", fileName)) {
			throw new Exception("文件名称不是由数字、字母和点组成");
		}
		String filePath = Constants.LOG_PATH;
		File targetFile = new File(filePath);

		if (!targetFile.exists())
			throw new Exception("文件不存在");

		filePath = filePath + "/" + fileName;

		download(filePath, fileName, request, response);
	}

	/**
	 * 查看日志文件内容
	 *
	 * @param fileName
	 * @throws Exception
	 */
	@RequestMapping(value = "viewFile")
	public String viewFile(String fileName, Page<String> page) throws Exception {
		request.setAttribute("fileName", fileName);

		if (StringUtil.isBlank(fileName))
			return "log/viewFile";

		String filePath = Constants.LOG_PATH;
		File targetFile = new File(filePath);

		if (!targetFile.exists())
			return "log/viewFile";

		filePath = filePath + "/" + fileName;
		page.setPageSize(50);
		int start = page.getStart();
		int end = page.getEnd();

		String ret = FileUtil.getSpecifyLine(filePath, start, end);
		int total = FileUtil.getFileLineNum(filePath);

		List<String> list = new ArrayList<String>();
		list.add(ret);
		page.setTotalNo(total);
		page.setResult(list);

		request.setAttribute("page", page);
		return "log/viewFile";
	}
}
