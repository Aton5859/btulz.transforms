package org.colorcoding.tools.btulz.orchestration;

import java.sql.Statement;

/**
 * SQL执行步骤
 * 
 * @author Niuren.Zhu
 *
 */
public interface ISqlExecutionActionStep extends IExecutionActionStep {

	/**
	 * 设置
	 * 
	 * @param statement
	 */
	void setStatement(Statement statement);

	/**
	 * 检查是否执行
	 * 
	 * @param value
	 *            运行的状态值
	 * @return 是否执行此步骤
	 */
	boolean check(Object value);

	/**
	 * 获取-运行值
	 * 
	 * @return
	 */
	String getRunOnValue();

	/**
	 * 设置-运行值
	 */
	void setRunOnValue(String value);

	/**
	 * 获取-运行sql语句
	 * 
	 * @return
	 */
	String getScript();

	/**
	 * 设置-运行sql语句
	 * 
	 * @param value
	 */
	void setScript(String value);

	/**
	 * 是否查询
	 * 
	 * @return
	 */
	boolean isQuery();

	/**
	 * 设置-是否查询
	 * 
	 * @return
	 */
	void setQuery(boolean value);
}
