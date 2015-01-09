package org.autumnlibs.valueproviders.servlet;

import org.autumnlibs.router.RouteMatcher;
import org.autumnlibs.valueproviders.servlet.matchers.HttpMethod;
import org.autumnlibs.valueproviders.servlet.matchers.HttpMethodRoutMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ServletValueProviders {
	static final String BASE_NAME = ServletValueProviders.class.getName()+"/values";

	public static void registerValues(final Map<String, Object> additionalInfo, final HttpServletRequest req, final HttpServletResponse resp) {
		additionalInfo.put(BASE_NAME+"/request", req);
		additionalInfo.put(BASE_NAME+"/response", resp);
	}

	public static HttpServletRequest getRequest(final Map<String, Object> additionalInfo) {
		return (HttpServletRequest) additionalInfo.get(BASE_NAME+"/request");
	}

	public static RouteMatcher<String> method(final HttpMethod httpMethod) {
		return new HttpMethodRoutMatcher(httpMethod);
	}
}
