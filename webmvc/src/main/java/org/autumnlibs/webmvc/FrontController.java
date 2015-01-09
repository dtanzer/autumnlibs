package org.autumnlibs.webmvc;

import org.autumnlibs.router.AbstractRoutes;
import org.autumnlibs.router.RequestInfo;
import org.autumnlibs.router.Router;

import java.util.Map;

public class FrontController<ViewType> {

	private final Router<String, ViewType> router;

	public FrontController(final AbstractRoutes<String, ViewType>... routes) {
		router = new Router<>(new WebPathMatcher(), routes);
	}

	public ViewType dispatchRequest(String path, Map<String, Object> additionalInfo) {
		RequestInfo<String> requestInfo = new RequestInfo<>(path, additionalInfo);
		return router.routeRequest(requestInfo);
	}
}
