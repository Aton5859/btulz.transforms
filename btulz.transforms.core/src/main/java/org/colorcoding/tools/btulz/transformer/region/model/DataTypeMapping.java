package org.colorcoding.tools.btulz.transformer.region.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.colorcoding.tools.btulz.Environment;
import org.colorcoding.tools.btulz.model.IProperty;
import org.colorcoding.tools.btulz.model.data.emDataSubType;
import org.colorcoding.tools.btulz.model.data.emDataType;
import org.colorcoding.tools.btulz.template.Parameter;
import org.colorcoding.tools.btulz.template.Variable;

/**
 * 数据类型映射
 * 
 * @author Niuren.Zhu
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "DataTypeMapping", namespace = Environment.NAMESPACE_BTULZ_TRANSFORMERS)
public class DataTypeMapping {
	/**
	 * 变量名称
	 */
	// public static final String PARAMETER_NAME = "DataTypeMapping";

	public DataTypeMapping() {

	}

	public DataTypeMapping(emDataType type, emDataSubType subType, String mapped) {
		this.setDataType(type);
		this.setSubType(subType);
		this.setMapped(mapped);
	}

	private emDataType DataType;

	@XmlAttribute(name = "DataType")
	public emDataType getDataType() {
		return DataType;
	}

	public void setDataType(emDataType DataType) {
		this.DataType = DataType;
	}

	private emDataSubType subType;

	@XmlAttribute(name = "SubType")
	public emDataSubType getSubType() {
		return subType;
	}

	public void setSubType(emDataSubType subType) {
		this.subType = subType;
	}

	private String mapped;

	@XmlAttribute(name = "Mapped")
	public String getMapped() {
		return mapped;
	}

	public void setMapped(String mappedType) {
		this.mapped = mappedType;
	}

	public String getMapped(IProperty property) throws Exception {
		// 处理映射中的变量
		String mapValue = this.getMapped();
		Variable[] variables = Variable.discerning(this.getMapped());
		if (variables.length > 0) {
			Parameter parameter = new Parameter();
			parameter.setName("Property");
			parameter.setValue(property);
			for (Variable variable : variables) {
				Object value = parameter.getValue(variable.getValuePath());
				if (value != null) {
					mapValue = mapValue.replace(variable.getOriginal(), String.valueOf(value));
				}
			}
		}
		return mapValue;
	}

	@Override
	public String toString() {
		return String.format("{data mapping: %s %s}", this.getMapped(), this.getDataType());
	}
}
