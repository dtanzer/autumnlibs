package org.autumnlibs.router;

import java.util.ArrayList;
import java.util.List;

public class RouteConfigurations<P, T> {
	private final List<RouteConfigurationInfo> routesList = new ArrayList<>();
	private PathMatcher<P> pathMatcher;

	public RouteConfigurations(final PathMatcher<P> pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	public void add(final P path, final RouteConfiguration<T> routeConfiguration, final RouteMatcher<P> routeMatcher) {
		routesList.add(new RouteConfigurationInfo(path, routeConfiguration, routeMatcher));
	}

	public RouteConfiguration<T> findRouteConfiguration(final RequestInfo<P> requestInfo) {
		for(RouteConfigurationInfo config : routesList) {
			if (pathMatcher.matchesPath(requestInfo.path(), config.path) && config.routeMatcher.matches(requestInfo)) {
				pathMatcher.enhanceRequestInfo(requestInfo);
				return config.routeConfiguration;
			}
		}
		return null;
	}

	private class RouteConfigurationInfo {
		private P path;
		private RouteConfiguration<T> routeConfiguration;
		private RouteMatcher<P> routeMatcher;

		private RouteConfigurationInfo(final P path, final RouteConfiguration<T> routeConfiguration, final RouteMatcher<P> routeMatcher) {
			this.path = path;
			this.routeConfiguration = routeConfiguration;
			this.routeMatcher = routeMatcher;
		}
	}
}
