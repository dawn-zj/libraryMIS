package cn.com.gs.ssm.libraryMIS.service.impl.zxing;

import cn.com.gs.common.util.ImageUtil;
import org.springframework.stereotype.Service;

@Service
public class BarcodeServiceImpl {
	/**
	 * 生成二维码
	 *
	 * */
	public byte[] genImage(String content) {
		byte[] imageData = ImageUtil.genBarcodeImage(content);
		return imageData;
	}
}
