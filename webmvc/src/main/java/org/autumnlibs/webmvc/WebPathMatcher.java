package org.autumnlibs.webmvc;

import org.autumnlibs.router.RequestInfo;

public class WebPathMatcher implements org.autumnlibs.router.PathMatcher<String> {
	@Override
	public boolean matchesPath(final String pathToResolve, final String configuredPath) {
		//FIXME we should be able to extract path variables!
		return pathToResolve.equals(configuredPath);
	}

	@Override
	public void enhanceRequestInfo(final RequestInfo<String> requestInfo) {

	}
}
