package cn.com.gs.common.resource;

import java.util.Hashtable;

public class ErrMsg {
	private static Hashtable<Integer, String> container = new Hashtable<Integer, String>();

	static {
		container.put(ErrCode.NO_ERROR_DESC, "没有对应的错误描述");
	}

	public static String getErrMsg(int errorNum) {
		String errorMsg = container.get(errorNum);
		if (errorMsg == null)
			return container.get(ErrCode.NO_ERROR_DESC);
		else
			return errorMsg;
	}
}