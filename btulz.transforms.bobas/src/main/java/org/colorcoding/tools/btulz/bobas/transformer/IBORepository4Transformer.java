package org.colorcoding.tools.btulz.bobas.transformer;

import org.colorcoding.ibas.bobas.bo.IBusinessObject;
import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.bobas.core.RepositoryException;

/**
 * 变形金刚业务仓库
 * 
 * @author Niuren.Zhu
 *
 */
public interface IBORepository4Transformer {
	/**
	 * 打开仓库
	 * 
	 * @return
	 * @throws RepositoryException
	 */
	boolean openRepository() throws RepositoryException;

	/**
	 * 关闭仓库
	 * 
	 * @throws RepositoryException
	 */
	void closeRepository() throws RepositoryException;

	/**
	 * 开始事务
	 * 
	 * @return
	 * @throws RepositoryException
	 */
	boolean beginTransaction() throws RepositoryException;

	/**
	 * 回滚事务
	 * 
	 * @throws RepositoryException
	 */
	void rollbackTransaction() throws RepositoryException;

	/**
	 * 提交事务
	 * 
	 * @throws RepositoryException
	 */
	void commitTransaction() throws RepositoryException;

	/**
	 * 保存业务对象
	 * 
	 * @param bo
	 *            业务对象
	 * @return
	 */
	<P extends IBusinessObject> OperationResult<P> saveData(P bo);

	/**
	 * 查询业务对象
	 * 
	 * @param criteria
	 *            查询条件
	 * @param boType
	 *            类型
	 * @return
	 */
	<P extends IBusinessObject> OperationResult<P> fetchData(ICriteria criteria, Class<P> boType);
}