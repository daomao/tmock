package org.googlecode.tmock.persistent.fastjson;

import java.nio.charset.Charset;

import org.googlecode.tmock.persistent.SerializationException;
import org.googlecode.tmock.persistent.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author zhongfeng
 * 
 */
public class FastJsonSerializer implements Serializer {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private final static FastJsonSerializer INST = new FastJsonSerializer();
	
	private final Logger log = LoggerFactory.getLogger(FastJsonSerializer.class);
	
	private FastJsonSerializer() {
	}


	public Object deserialize(String content)
			throws SerializationException {
		if (content == null) {
			return null;
		}
		try {
			return JSON.parse(content);
		} catch (Exception ex) {
			log.error("FastJson deserialize error",ex);
			throw new SerializationException("Could not read JSON: "
					+ ex.getMessage(), ex);
		}
	}


	public  String serialize(Object t) throws SerializationException {
		if (t == null) {
			return "";
		}
		try {
			return JSON.toJSONString(t,SerializerFeature.WriteClassName);
		} catch (Exception ex) {
			log.error("FastJson serialize error",ex);
			throw new SerializationException("Could not write JSON: "
					+ ex.getMessage(), ex);
		}
	}

	public final static Serializer getInstance() {
		return INST;
	}

}
