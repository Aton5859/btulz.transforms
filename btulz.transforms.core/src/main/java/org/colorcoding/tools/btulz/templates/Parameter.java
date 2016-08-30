package org.colorcoding.tools.btulz.templates;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 参数
 * 
 * @author Niuren.Zhu
 *
 */
public class Parameter {
	public Parameter() {

	}

	public Parameter(String name, Object value) {
		this.setName(name);
		this.setValue(value);
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	protected Object getValue(Object value, String path)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (path != null && !path.equals("") && value != null) {
			String curPath = path;
			String nextPath = null;
			int indexPath = path.indexOf(").");// 仅支持方法，所以为此
			if (indexPath > 0) {
				curPath = path.substring(0, indexPath + 1);
				nextPath = path.substring(indexPath + 2, path.length());
			}
			// 获取当前路径参数
			Object tmpValue = null;
			Class<?> type = value.getClass();
			String mName = curPath;
			Object[] pathArgs = {};
			if (curPath.indexOf("(") > 0) {
				// 存在参数
				mName = curPath.substring(0, curPath.indexOf("("));
				String tmp = curPath.substring(curPath.indexOf("(") + 1, curPath.indexOf(")"));
				if (tmp.length() > 0) {
					if (tmp.length() == 1) {
						pathArgs = new Object[1];
						pathArgs[0] = tmp;
					} else {
						String[] tmps = tmp.split(",");
						pathArgs = new Object[tmps.length];
						for (int i = 0; i < tmps.length; i++) {
							pathArgs[i] = tmps[i];
						}
					}
				}
			}
			for (Method method : type.getDeclaredMethods()) {
				if (method.getName().equals(mName) && (method.getParameterCount() == pathArgs.length)) {
					tmpValue = method.invoke(value, pathArgs);
					break;
				}
			}
			// 处理下级路径
			if (nextPath != null && !nextPath.equals("")) {
				return this.getValue(tmpValue, nextPath);
			}
			// 路径值未找到
			return tmpValue;
		}
		return value;
	}

	public Object getValue(String path) throws Exception {
		// like,getValue().toString(xml)
		return this.getValue(this.getValue(), path);
	}

	public String toString() {
		return String.format("Parameter %s %s", this.getName(), this.getValue());
	}
}
