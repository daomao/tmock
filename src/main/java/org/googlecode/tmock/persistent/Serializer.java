package org.googlecode.tmock.persistent;

/**
 * @author zhongfeng
 *
 */
public interface Serializer {

	public abstract Object deserialize(String content)
			throws SerializationException;

	public abstract String serialize(Object t)
			throws SerializationException;

}