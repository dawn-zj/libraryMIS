package cn.com.gs.ssm.libraryMIS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.*;

@Controller
public class BaseController {

	/**
	 * 设置扩展属性
	 * 
	 * @param extMsg
	 * @param resultMap
	 */
	private void setExtMsg(HashMap<String, Object> resultMap, Properties extMsg) {
		if (extMsg == null)
			return;

		Enumeration<Object> en = extMsg.keys();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			resultMap.put(key, extMsg.get(key));
		}
	}

	/**
	 * 获取成功MAP
	 * 
	 * @param pageMsg
	 * @param extMsg
	 * @return
	 */
	protected HashMap<String, Object> getSuccMap() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 获取成功MAP
	 * 
	 * @param pageMsg
	 * @param extMsg
	 * @return
	 */
	protected HashMap<String, Object> getSuccMap(String pageMsg) {
		HashMap<String, Object> resultMap = getSuccMap();
		resultMap.put("message", pageMsg);
		return resultMap;
	}

	/**
	 * 获取成功MAP
	 * 
	 * @param extMsg
	 * @return
	 */
	protected HashMap<String, Object> getSuccMap(Properties extMsg) {
		HashMap<String, Object> resultMap = getSuccMap();
		setExtMsg(resultMap, extMsg);
		return resultMap;
	}

	/**
	 * 获取成功MAP
	 * 
	 * @param pageMsg
	 * @param extMsg
	 * @return
	 */
	protected HashMap<String, Object> getSuccMap(String pageMsg, Properties extMsg) {
		HashMap<String, Object> resultMap = getSuccMap(pageMsg);
		setExtMsg(resultMap, extMsg);
		return resultMap;
	}

	/**
	 * 获取异常MAP
	 * 
	 * @param logMsg
	 *            日志中记录信息
	 * @param e
	 *            异常
	 * @return
	 */
	protected HashMap<String, Object> getErrMap(String pageMsg) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		resultMap.put("message", pageMsg);
		return resultMap;
	}





	/**
	 * 获取MAP
	 * 
	 * @param extMsg
	 *            扩展信息
	 * 
	 * @return
	 */
	protected HashMap<String, Object> getMap(Properties extMsg) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		setExtMsg(resultMap, extMsg);
		return resultMap;
	}

	/**
	 * 返回ModelAndView
	 * 
	 * @param resultMap
	 * @return
	 */
	protected ModelAndView getModelAndView(Map<String, Object> resultMap) {
		return new ModelAndView(new MappingJacksonJsonView(), resultMap);
	}

	/**
	 * 下载文件
	 * 
	 * @param zipPath
	 * @param request
	 * @param response
	 */
	protected void download(String filePath, String fileName, HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

		File zipFile = new File(filePath);
		FileInputStream fis = null;
		OutputStream os = null;
		if (zipFile.exists()) {
			try {
				fis = new FileInputStream(zipFile);
				os = response.getOutputStream();
				int length = 0;
				byte[] b = new byte[1024];
				while ((length = fis.read(b)) > 0) {
					os.write(b, 0, length);
				}
			} catch (Exception e) {
				//LoggerUtil.errorlog("download file error, ", e);
			} finally {
				if (os != null) {
					try {
						os.close();
					} catch (Exception e) {
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (Exception e) {
					}
				}
			}
		}
	}

	/**
	 * 图片上传
	 *
	 * @param request
	 * @param photoName   图片文件名称
	 * @param photoSuffix 图片后缀
	 * @param photoSize   图片大小
	 * @param description 图片描述
	 * @throws Exception
	 * @return 图片数据
	 */
//	protected byte[] getPhoto(HttpServletRequest request, String photoName, Long photoSize, String description, String... photoSuffix) throws Exception {
//		byte[] photoByte = null;
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//		if (multipartResolver.isMultipart(request)) {
//			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//			MultipartFile file = multiRequest.getFile(photoName);
//			if (file != null && file.getSize() > 0) {
//				photoByte = file.getBytes();
//				String photoType = "." + FileUtil.getFileSuffix(file.getOriginalFilename());
//				boolean invalidSuffix = true;
//				List<String> suffix = Arrays.asList(photoSuffix);
//				for (int i = 0; i < photoSuffix.length; i++) {
//					if (photoType.equals(photoSuffix[i])) {
//						invalidSuffix=false;
//						break;
//					}
//				}
//				if (invalidSuffix) {
//					//throw new WebDataException("请选择" + suffix + "图片类型");
//				}
//
//
//				try {
//					com.itextpdf.text.Image.getInstance(file.getBytes());
//				} catch (Exception e) {
//					throw new WebDataException(description + "图片无效");
//				}
//
//				long fileSize = file.getSize();
//				if (fileSize / 1024 > photoSize) {
//					throw new WebDataException(description + "图片不超过" + photoSize + "KB");
//				}
//			}
//		}
//		return photoByte;
//	}
}
