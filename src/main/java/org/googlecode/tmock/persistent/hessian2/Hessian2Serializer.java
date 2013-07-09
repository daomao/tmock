/**
 * 
 */
package org.googlecode.tmock.persistent.hessian2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.googlecode.tmock.persistent.SerializationException;
import org.googlecode.tmock.persistent.SerializationUtils;
import org.googlecode.tmock.persistent.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;

/**
 * @author zhongfeng
 * 
 */
public class Hessian2Serializer implements Serializer {
	
	private final Logger log = LoggerFactory
			.getLogger(Hessian2Serializer.class);

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private static final SerializerFactory _serializerFactory = new SerializerFactory();

	private final static Hessian2Serializer INST = new Hessian2Serializer();

	/**
	 * 
	 */
	private Hessian2Serializer() {
	}

	private Object deserializeH2(byte[] bytes)
			throws SerializationException {
		if (SerializationUtils.isEmpty(bytes)) {
			return null;
		}
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		Hessian2Input hi = new Hessian2Input(is);
		hi.setSerializerFactory(_serializerFactory);
		Object obj = null;
		try {
			obj =  hi.readObject();
		} catch (IOException e) {
			log.error("Hessian2 deserializer error:", e);
			throw new SerializationException("Could not read hessian2: "
					+ e.getMessage(), e);
		} finally {
			try {
				if (is != null)
					is.close();
				if (hi != null)
					hi.close();
			} catch (IOException e) {
				log.error("Hessian2 close in stream error:", e);
			}
		}
		return obj;
	}

	private  byte[] serializeH2(Object t) throws SerializationException {
		if (t == null) {
			return SerializationUtils.EMPTY_ARRAY;
		}
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		Hessian2Output h2out = new Hessian2Output(bout);
		h2out.setSerializerFactory(_serializerFactory);
		byte[] bytes = null;
		try {
			h2out.writeObject(t);
			h2out.flush();
			bytes = bout.toByteArray();
		} catch (IOException e) {
			log.error("Hessian2 serializer error:", e);
			throw new SerializationException("Could not write hessian2: "
					+ e.getMessage(), e);
		} finally {
			try {
				if (bout != null)
					bout.close();
				if (h2out != null)
					h2out.close();
			} catch (IOException e) {
				log.error("Hessian2 close out stream error:", e);
			}
		}
		return bytes;

	}

	@Override
	public Object deserialize(String content)
			throws SerializationException {
		byte[] hessian2Bytes = Base64.decodeBase64(content);
		return deserializeH2(hessian2Bytes);
	}

	@Override
	public String serialize(Object t) throws SerializationException {
		byte[] hessian2Bytes = serializeH2(t);
		return Base64.encodeBase64String(hessian2Bytes);
	}

	public final static Serializer getInstance() {
		return INST;
	}
}
