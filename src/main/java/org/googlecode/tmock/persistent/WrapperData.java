package org.googlecode.tmock.persistent;

import java.io.Serializable;

/**
 * @author zhongfeng
 *
 */
@Deprecated
public class WrapperData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Class<?> objType;
	private String content;
	
	/**
	 * 
	 */
	public WrapperData() {
	}
	/**
	 * @param objType
	 * @param content
	 */
	public WrapperData(Class<?> objType, String content) {
		this.objType = objType;
		this.content = content;
	}
	public Class<?> getObjType() {
		return objType;
	}
	public void setObjType(Class<?> objType) {
		this.objType = objType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "CacheData [content=" + content + ", objType="
				+ objType + "]";
	}
	
}
