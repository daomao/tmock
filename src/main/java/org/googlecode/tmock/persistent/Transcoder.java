package org.googlecode.tmock.persistent;

/**
 * @author zhongfeng
 *
 */
public class Transcoder {

	private Serializer serializer;

	/**
	 * @param serializer
	 */
	public Transcoder(Serializer serializer) {
		this.serializer = serializer;
	}

	public Object decode(String cachestr) {
		if (cachestr == null)
			return null;
		return getSerializer().deserialize(cachestr);
	}

	public String encode(Object obj) {
		return getSerializer().serialize(obj);

	}

	public Serializer getSerializer() {
		return serializer;
	}

	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}

	public static Transcoder newInstance(Serializer serializer) {
		return new Transcoder(serializer);
	}
}
