package cn.com.gs.common.util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.com.gs.common.define.Constants;
import cn.com.gs.common.exception.NetGSRuntimeException;
import cn.com.gs.common.resource.ErrCode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StringUtil {

	/**
	 * 将指定字符串转换为整数类型, 若为空或者格式不正确,则使用默认值
	 * 
	 * @param sValue
	 *            要转换的字符串
	 * @param defaultValue
	 *            int类型默认值
	 * @return int型整数
	 */
	public static int parseIntWithDefault(String sValue, int defaultValue) {
		if (sValue == null) {
			return defaultValue;
		}
		int v = defaultValue;
		try {
			v = Integer.parseInt(sValue.trim());
		} catch (Throwable e) {
		}
		return v;
	}

	/**
	 * 将指定字符串去除两端的空格, 若为空或者格式不正确,则使用默认值
	 * 
	 * @param sValue
	 *            要去除空格的字符串
	 * @param defaultValue
	 *            默认值
	 * @return 去除完两端空格的字符串
	 */
	public static String parseStringWithDefault(String sValue, String defaultValue) {
		if (sValue == null) {
			return defaultValue;
		} else {
			return sValue.trim();
		}
	}

	/**
	 * 将指定字符串转成字节数组
	 * 
	 * @param str
	 *            字符串
	 * @return byte[]
	 */
	public static byte[] getBytes(String str) {
		byte[] bs = new byte[0];
		if (str != null)
			try {
				bs = str.getBytes(Constants.UTF_8);
			} catch (Exception e) {
				throw new NetGSRuntimeException(e.getMessage());
			}
		return bs;
	}

	public static String getString(String str) {
		try {
			return new String(str.getBytes("iso8859-1"), Constants.UTF_8);
		} catch (Exception e) {
			throw new NetGSRuntimeException(e.getMessage());
		}
	}

	/**
	 * 将字节数组转成字符串
	 * 
	 * @param data
	 *            字节数组
	 * @return byte[]
	 */
	public static String getString(byte[] data) {
		try {
			return new String(data, Constants.UTF_8);
		} catch (Exception e) {
			throw new NetGSRuntimeException(e.getMessage());
		}
	}

	/**
	 * 去除字符串
	 * 
	 * @param src：1
	 * @param delStr：1
	 * @param addStr：1
	 * @return String：字符串
	 */
	public static String getString(String src, String delStr, String addStr) {
		StringBuffer result = new StringBuffer();
		if (src == null) {
			return result.toString();
		}
		String[] tempArray = src.split(",");
		for (String tempStr : tempArray) {
			if (!tempStr.equals(delStr)) {
				result.append(tempStr).append(",");
			}
		}
		result.append(addStr);
		return result.toString();
	}

	/**
	 * 判断字符串为空
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return boolean
	 */
	public static boolean isBlank(String str) {
		if (str == null || str.trim().length() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return boolean
	 */
	public static boolean isNotBlank(String str) {
		if (str != null && str.trim().length() > 0)
			return true;
		else
			return false;
	}

	/**
	 * 返回指定长度的数组, 不够左补0x00, 超过从左开始截取
	 * 
	 * @param bs
	 * @param len
	 * @return
	 */
	public static byte[] padLeft(byte[] bs, int len) {
		if (bs == null || len <= 0)
			return null;

		byte[] data = new byte[len];
		if (bs.length == data.length)
			return bs;

		if (bs.length < data.length) {
			System.arraycopy(bs, 0, data, data.length - bs.length, bs.length);
			return data;
		}

		System.arraycopy(bs, 0, data, 0, len);
		return data;
	}

	/**
	 * 返回指定长度的数组, 不够返回原始数组, 超过从左开始截取
	 * 
	 * @param bs
	 * @param len
	 * @return
	 */
	public static byte[] subLeft(byte[] bs, int len) {
		if (bs == null || len <= 0)
			return null;

		byte[] data = new byte[len];
		if (bs.length <= data.length)
			return bs;

		System.arraycopy(bs, 0, data, 0, len);
		return data;
	}

	/**
	 * 去掉补位
	 * 
	 * @param bs
	 * @return
	 */
	public static byte[] clearPad(byte[] bs) {
		return new BigInteger(bs).toByteArray();
	}
	
	/**
	 * 将map转为json字符串
	 * @param map
	 */
	public static String mapToJson(Map<Object,Object> map) {
		return JSONArray.fromObject(map).toString();
	}
	
	/**
	 * 将数组转为指定class类型
	 * @param array
	 * @param cls
	 * @return
	 */
	public static <T> T arrayToObj(byte[] array, Class<T> cls){
		JSONObject jsonObject =JSONObject.fromObject(array);
		T obj = (T)JSONObject.toBean(jsonObject, cls);
		return obj;
	}
	
	/**
	 * 将json字符串转为指定class类型
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> T jsonToObj(String json, Class<T> cls){
		JSONObject jsonObject =JSONObject.fromObject(json);
		T obj = (T)JSONObject.toBean(jsonObject, cls);
		return obj;
	}

	/**
	 * 判断是否以某字符串开始
	 * @param msg
	 * @param msgPrefix
	 * @return
	 */
	public static boolean startsWithAny(String msg, String... msgPrefix){
		boolean isSlave = false;
		List<String> suffix = Arrays.asList(msgPrefix);
		for (int i = 0; i < msgPrefix.length; i++) {
			if (msg.startsWith(msgPrefix[i])) {
				isSlave = true;
				break;
			}
		}
		return isSlave;
	}
}
