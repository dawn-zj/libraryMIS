package cn.com.gs.common.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat sdf_all = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
		String data = getDate();
		System.out.println(data);
	}

	/**
	 * 获取当前时间：1970年1月1日至今的毫秒数
	 * 
	 * @return 毫秒
	 * 
	 */
	public static long getTime() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取当前天
	 * 
	 * @return 年-月-日
	 * 
	 */
	public static String getDate() {
		return sdf.format(new Date());
	}

	/**
	 * 获取当前天：毫秒—天（年-月-日）
	 * 
	 * @param time
	 *            毫秒数
	 * @return 年-月-日
	 * 
	 */
	public static String getDate(Long time) {
		if (time == null) {
			return "";
		}
		return sdf.format(new Date(time));
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 年-月-日 时:分:秒
	 * 
	 */
	public static String getDateTime() {
		return sdf_all.format(new Date());
	}

	/**
	 * 获取当前天：时间
	 * 
	 * @param time
	 *            毫秒数
	 * @return 年-月-日 时:分:秒
	 * 
	 */
	public static String getDateTime(Long time) {
		if (time == null) {
			return "";
		}
		return sdf_all.format(new Date(time));
	}

	public static long getTime(String dataTime) throws ParseException {
		if (dataTime == null) {
			return -1L;
		}
		return sdf.parse(dataTime).getTime();
	}

}
