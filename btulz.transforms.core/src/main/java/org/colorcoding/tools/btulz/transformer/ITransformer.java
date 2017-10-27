package org.colorcoding.tools.btulz.transformer;

/**
 * 转换器
 * 
 * @author Niuren.Zhu
 *
 */
public interface ITransformer {
	/**
	 * 是否保留上次结果
	 * 
	 * @return
	 */
	boolean isKeepResults();

	/**
	 * 设置是否保留上次结果
	 * 
	 * @param value
	 * @return
	 */
	void setKeepResults(boolean value);

	/**
	 * 是否中断发生错误时
	 * 
	 * @return
	 */
	boolean isInterruptOnError();

	/**
	 * 设置是否中断发生错误时
	 * 
	 * @param value
	 * @return
	 */
	void setInterruptOnError(boolean value);

	/**
	 * 转换
	 * 
	 * @throws Exception
	 */
	void transform() throws Exception;
}
