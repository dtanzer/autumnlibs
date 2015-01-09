package org.autumnlibs.valueproviders.servlet.matchers;

public enum HttpMethod {
	GET("GET"), POST("POST");

	private final String methodName;


	HttpMethod(final String methodName) {
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}
}
