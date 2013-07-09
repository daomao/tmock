package org.googlecode.tmock.persistent;

import java.util.HashMap;
import java.util.Map;

import org.googlecode.tmock.persistent.fastjson.FastJsonSerializer;
import org.googlecode.tmock.persistent.hessian2.Hessian2Serializer;
import org.googlecode.tmock.persistent.jdk.JDKSerializer;


/**
 * @author zhongfeng
 * 
 */
public class TransCoderFactory {

	public static enum CoderType {
		fastjson, hessian2, jdk
	};

	private final static Map<CoderType, Transcoder> TRANSCODER_MAP = new HashMap<CoderType, Transcoder>();

	static {
		TRANSCODER_MAP.put(CoderType.fastjson, Transcoder
				.newInstance(FastJsonSerializer.getInstance()));

		TRANSCODER_MAP.put(CoderType.hessian2, Transcoder
				.newInstance(Hessian2Serializer.getInstance()));
		TRANSCODER_MAP.put(CoderType.jdk, Transcoder.newInstance(JDKSerializer
				.getInstance()));
	}

	public static Transcoder getTranscoder(CoderType type) {
		return TRANSCODER_MAP.get(type);
	}
}
