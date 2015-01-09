package org.autumnlibs.router;

public abstract class AbstractRoutes<PathType, ControllerType> {
	private final Class<ControllerType> routeResultClass;
	private RouteConfigurations<PathType, ControllerType> routeConfigurations;

	protected AbstractRoutes(Class<ControllerType> routeResultClass) {
		this.routeResultClass = routeResultClass;
	}

	protected abstract void configureRoutes();

	protected RouteConfiguration<ControllerType> route(PathType path) {
		return route(path, RouteMatcher.matchesAll());
	}

	public RouteConfiguration<ControllerType> route(final PathType path, final RouteMatcher<PathType> routeMatcher) {
		RouteConfiguration<ControllerType> routeConfiguration = RouteConfiguration.forResultClass(routeResultClass);
		routeConfigurations.add(path, routeConfiguration, routeMatcher);
		return routeConfiguration;
	}

	public void setRouteConfigurations(final RouteConfigurations routeConfigurations) {
		this.routeConfigurations = routeConfigurations;
	}
}
