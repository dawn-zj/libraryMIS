package cn.com.gs.ssm.libraryMIS.aop;

import cn.com.gs.common.util.StringUtil;
import cn.com.gs.common.util.logger.LoggerUtil;
import cn.com.gs.ssm.libraryMIS.dataSource.DynamicDataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 切面，在进入service前判断该用哪个数据源
 */
public class DataSourceAspect {
	private List<String> slaveMethodPattern = new ArrayList<String>();

	private static final String[] defaultSlaveMethodStart = new String[]{ "select", "search", "get" };

	private String[] slaveMethodStart;

	/**
	 * 用户指定slave的方法名前缀
	 * @param slaveMethodStart
	 */
	public void setSlaveMethodStart(String[] slaveMethodStart) {
		this.slaveMethodStart = slaveMethodStart;
	}

	public String[] getSlaveMethodStart() {
		if(this.slaveMethodStart == null){
			// 没有指定，使用默认
			return defaultSlaveMethodStart;
		}
		return slaveMethodStart;
	}

	/**
	 * 读取事务管理中的策略
	 *
	 * @param txAdvice
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void setTxAdvice(TransactionInterceptor txAdvice) throws Exception {
		LoggerUtil.debugLog( "----设置事务策略----");
		if (txAdvice == null) {
			// 没有配置事务管理策略
			return;
		}

		//从txAdvice获取到策略配置信息
		TransactionAttributeSource transactionAttributeSource = txAdvice.getTransactionAttributeSource();
		if (!(transactionAttributeSource instanceof NameMatchTransactionAttributeSource)) {
			return;
		}

		//使用反射技术获取到NameMatchTransactionAttributeSource对象中的nameMap属性值
		NameMatchTransactionAttributeSource matchTransactionAttributeSource = (NameMatchTransactionAttributeSource) transactionAttributeSource;
		Field nameMapField = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");
		nameMapField.setAccessible(true); //设置该字段可访问
		//获取nameMap的值
		Map<String, TransactionAttribute> map = (Map<String, TransactionAttribute>) nameMapField.get(matchTransactionAttributeSource);

		//遍历nameMap
		for (Map.Entry<String, TransactionAttribute> entry : map.entrySet()) {
			if (!entry.getValue().isReadOnly()) {//判断之后定义了ReadOnly的策略才加入到slaveMethodPattern
				continue;
			}
			slaveMethodPattern.add(entry.getKey());
		}

	}


	public void before(JoinPoint pjp) {
		// 获取方法名
		String methodName = pjp.getSignature().getName();

		boolean isSlave = false;

		if (slaveMethodPattern.isEmpty()) {
			// 当前Spring容器中没有配置事务策略，采用方法名匹配方式
			isSlave = isSlave(methodName);
		}else{
			// 使用策略规则匹配
			for (String mappedName : slaveMethodPattern) {
				if (isMatch(methodName, mappedName)) {
					isSlave = true;
					break;
				}
			}
		}

		if(isSlave){
			// 走读库
			DynamicDataSourceHolder.markSlave();
			LoggerUtil.debugLog(methodName + "----读库");
		} else{
			// 走写库
			DynamicDataSourceHolder.markMaster();
			LoggerUtil.debugLog(methodName + "----写库");
		}

	}

	/**
	 * 判断是否为读操作
	 *
	 * @param methodName
	 * @return
	 */
	private Boolean isSlave(String methodName) {
		// 方法名以query、find、get开头的方法名走从库
		return StringUtil.startsWithAny(methodName, "query", "find", "get");
	}

	/**
	 * 通配符匹配
	 *
	 * Return if the given method name matches the mapped name.
	 * <p>
	 * The default implementation checks for "xxx*", "*xxx" and "*xxx*" matches, as well as direct
	 * equality. Can be overridden in subclasses.
	 *
	 * @param methodName the method name of the class
	 * @param mappedName the name in the descriptor
	 * @return if the names match
	 * @see org.springframework.util.PatternMatchUtils#simpleMatch(String, String)
	 */
	protected boolean isMatch(String methodName, String mappedName) {
		return PatternMatchUtils.simpleMatch(mappedName, methodName);
	}

}
