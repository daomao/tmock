package org.googlecode.tmock.persistent;

/**
 * @author zhongfeng
 *
 */
@Deprecated
public abstract class AbstractTranscoder {

	public AbstractTranscoder() {
	}

	public abstract Serializer getSerializer();

	public Object decode(String cachestr) {
		if (cachestr == null)
			return null;
		WrapperData cacheData = (WrapperData) getSerializer().deserialize(cachestr);
		Object value = getRawInstance(cacheData);
		return value;
	}

	public String encode(Object obj) {
		WrapperData data = buildCacheData(obj);
		try {
			return getSerializer().serialize(data);
		} catch (SerializationException e) {
			throw e;
		}
		
	}

	/**
	 * @param obj
	 * @return
	 */
	private WrapperData buildCacheData(Object obj) {
		Class<?> cls = obj.getClass();
		String jsonStr = getSerializer().serialize(obj);
		WrapperData cacheData = new WrapperData(cls, jsonStr);
		return cacheData;
	}

	/**
	 * @param cacheData
	 * @return
	 */
	private Object getRawInstance(WrapperData data) {
		return getSerializer().deserialize(data.getContent());
	}

}