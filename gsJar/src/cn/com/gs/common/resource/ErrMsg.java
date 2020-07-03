package cn.com.gs.common.resource;

import java.util.Hashtable;

public class ErrMsg {
	private static Hashtable<Integer, String> container = new Hashtable<Integer, String>();

	static {
		container.put(ErrCode.NO_ERROR_DESC, "没有对应的错误描述");

		container.put(ErrCode.GEN_PHOTO_ERROR, "生成图片错误");

		container.put(ErrCode.PAGE_NUM_OVER_LIMIT_IN_PDF, "页码超出PDF文件范围");
	}

	public static String getErrMsg(int errorNum) {
		String errorMsg = container.get(errorNum);
		if (errorMsg == null)
			return container.get(ErrCode.NO_ERROR_DESC);
		else
			return errorMsg;
	}
}