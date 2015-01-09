package org.autumnlibs.valueproviders.servlet.matchers;

import org.autumnlibs.router.RequestInfo;
import org.autumnlibs.router.RouteMatcher;
import org.autumnlibs.valueproviders.servlet.ServletValueProviders;

import javax.servlet.http.HttpServletRequest;

public class HttpMethodRoutMatcher extends RouteMatcher {
	private HttpMethod httpMethod;

	public HttpMethodRoutMatcher(final HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	@Override
	protected boolean matches(final RequestInfo requestInfo) {
		HttpServletRequest request = ServletValueProviders.getRequest(requestInfo.additionalInfo());
		String method = request.getMethod();
		return method.equals(httpMethod.getMethodName());
	}
}
