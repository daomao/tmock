package org.googlecode.tmock.persistent;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @author zhongfeng
 *
 */
class StringSerializer {

	private final Charset charset;

	public StringSerializer() {
		this(Charset.forName("UTF8"));
	}

	public StringSerializer(Charset charset) {
		this.charset = charset;
	}

	public String deserialize(byte[] bytes) {
		String ret = null;
		try {
			ret = (bytes == null ? null : new String(bytes, charset.name()));
		} catch (UnsupportedEncodingException e) {
		}
		return ret;
	}

	public byte[] serialize(String string) {
		byte[] ret = null;
		try {
			ret = (string == null ? null : string.getBytes(charset.name()));
		} catch (UnsupportedEncodingException e) {
		}
		return ret;
	}

}