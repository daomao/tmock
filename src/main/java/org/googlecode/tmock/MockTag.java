package org.googlecode.tmock;

import java.util.ArrayList;

/**
 * @author zhongfeng
 * 
 */
public class MockTag {

	private final static ThreadLocal<String> TESTCASE_DIR_TAG = new ThreadLocal<String>() {
		protected String initialValue() {
			return "";
		};
	};

	private final static ThreadLocal<ArrayList<String>> MUTIL_TAG = new ThreadLocal<ArrayList<String>>() {
		protected ArrayList<String> initialValue() {
			return new ArrayList<String>(0);
		};
	};

	/**
	 * 设置标识
	 * 
	 * @param tag
	 */
	public static void setTestCaseDirName(String dirName) {
		TESTCASE_DIR_TAG.set(dirName);
	}

	/**
	 * 获取标识
	 * 
	 * @param tag
	 * @return
	 */
	public static String getTestCaseDirName() {
		String tag = TESTCASE_DIR_TAG.get();
		if (tag == null)
			tag = "";
		return tag;
	}

	/**
	 * 设置标识
	 * 
	 * @param tag
	 */
	public static void setMultiTags(ArrayList<String> tags) {
		MUTIL_TAG.set(tags);
	}

	/**
	 * 获取标识
	 * 
	 * @param tag
	 * @return
	 */
	public static String getMultiTag() {
		ArrayList<String> tags = MUTIL_TAG.get();
		String tag = "";
		if (tags != null && (!tags.isEmpty())) {
			tag = tags.get(0);
			tags.remove(0);
		}
		return tag;
	}

	/**
	 * 清除标识
	 */
	public static void clear() {
		TESTCASE_DIR_TAG.remove();
		MUTIL_TAG.remove();
	}
}
