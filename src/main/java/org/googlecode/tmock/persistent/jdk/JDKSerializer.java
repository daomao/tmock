/**
 * 
 */
package org.googlecode.tmock.persistent.jdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.googlecode.tmock.persistent.SerializationException;
import org.googlecode.tmock.persistent.SerializationUtils;
import org.googlecode.tmock.persistent.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zhongfeng
 * 
 */
public class JDKSerializer implements Serializer {

	private final Logger log = LoggerFactory.getLogger(JDKSerializer.class);

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	public final static JDKSerializer INST = new JDKSerializer();

	/**
	 * 
	 */
	private JDKSerializer() {
	}

	public Object deserializeH2(byte[] bytes) throws SerializationException {
		if (SerializationUtils.isEmpty(bytes)) {
			return null;
		}
		ByteArrayInputStream input = new ByteArrayInputStream(bytes);  
	    ObjectInputStream objectIn = null;  
	    Object obj = null;  
	    try {  
	        objectIn = new ObjectInputStream(input);      
	        obj= objectIn.readObject();    
		} catch (Exception e) {
			log.error("JDK deserializer error:", e);
			throw new SerializationException("Could not read JDK: "
					+ e.getMessage(), e);
		} finally {
			try {
				if (objectIn != null)
					objectIn.close();
				if (input != null)
					input.close();
			} catch (IOException e) {
				log.error("JDK close in stream error:", e);
			}
		}
		return obj;
	}

	public byte[] serializeH2(Object t) throws SerializationException {
		if (t == null) {
			return SerializationUtils.EMPTY_ARRAY;
		}
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ObjectOutputStream objectOut = null;
		try {
			objectOut = new ObjectOutputStream(output);
			objectOut.writeObject(t);
		} catch (IOException e) {
			log.error("JDK serializer error:", e);
			throw new SerializationException("Could not write JDK: "
					+ e.getMessage(), e);
		} finally {
			try {
				if (objectOut != null)
					objectOut.close();
				if (output != null)
					output.close();
			} catch (IOException e) {
				log.error("JDK close out stream error:", e);
			}
		}
		return output.toByteArray();

	}

	public Object deserialize(String content) throws SerializationException {
		byte[] bytes = Base64.decodeBase64(content);
		return deserializeH2(bytes);
	}

	public String serialize(Object t) throws SerializationException {
		byte[] bytes = serializeH2(t);
		return Base64.encodeBase64String(bytes);
	}

	public <T> T deserialize(String content, Class<T> cls)
			throws SerializationException {
		return cls.cast(deserialize(content));
	}

	public final static Serializer getInstance() {
		return INST;
	}

}
