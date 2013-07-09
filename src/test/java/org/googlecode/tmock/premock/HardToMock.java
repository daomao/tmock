package org.googlecode.tmock.premock;

public final class HardToMock {
	public final String finalMethod() {
		return "HardToMock value";
	}
	public final native String nativeMethod();
}
