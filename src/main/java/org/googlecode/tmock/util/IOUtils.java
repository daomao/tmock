package org.googlecode.tmock.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.googlecode.tmock.persistent.SerializationException;
import org.googlecode.tmock.persistent.TransCoderFactory;
import org.googlecode.tmock.persistent.TransCoderFactory.CoderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zhongfeng
 * 
 */
public class IOUtils {

	private final static Logger logger = LoggerFactory.getLogger(IOUtils.class);

	/**
	 * 默认存储编码
	 */
	public final static String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 从文件fileName中，反序列化出cls的实例
	 * 
	 * @param <T>
	 * @param cls
	 * @param fileName
	 *            绝对路径
	 * @return cls的实例
	 * @throws SerializationException
	 * @throws IOException
	 */
	public static Object readObjFromFile(String fileName, CoderType coderType)
			throws SerializationException, IOException {
		logger.info("Read Dir : {}", fileName);

		String str = FileUtils.readFileToString(new File(fileName),
				DEFAULT_ENCODING);
		return TransCoderFactory.getTranscoder(coderType).decode(str);
	}

	/**
	 * 实例序列化后，存储到fileName中；
	 * 
	 * @param <T>
	 * @param t
	 * @param fileName
	 *            绝对路径
	 * @throws SerializationException
	 * @throws IOException
	 */
	public static void writeObjToFile(Object t, String fileName,
			CoderType coderType) throws SerializationException, IOException {
		logger.info("Save Dir : {}", fileName);

		String str = TransCoderFactory.getTranscoder(coderType).encode(t);
		File f = new File(fileName);
		if (f.exists()) {
			logger.warn(
					"File:{},is alreay exists,will be override it,check it",
					fileName);
		}
		FileUtils.writeStringToFile(f, str, DEFAULT_ENCODING);
	}
}
