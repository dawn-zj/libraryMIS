package cn.com.gs.ssm.libraryMIS.service.impl.zxing;

import org.springframework.stereotype.Service;
import cn.com.gs.common.util.zxing.BarcodeUtil;

@Service
public class BarcodeServiceImpl {
	/**
	 * 生成二维码
	 * 
	 * */
	public byte[] genImage(String content) {
		byte[] imageData = BarcodeUtil.genBarcodeImage(content);
		return imageData;
	}
}
