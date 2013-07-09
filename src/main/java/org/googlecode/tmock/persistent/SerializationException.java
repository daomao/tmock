
package org.googlecode.tmock.persistent;

/**
 * @author zhongfeng
 *
 */
public class SerializationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new <code>SerializationException</code> instance.
	 *
	 * @param msg
	 */
	public SerializationException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a new <code>SerializationException</code> instance.
	 *
	 * @param msg
	 * @param cause
	 */
	public SerializationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
