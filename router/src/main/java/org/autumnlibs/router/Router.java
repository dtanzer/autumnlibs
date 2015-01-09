package org.autumnlibs.router;

import java.util.HashMap;
import java.util.Map;

public class Router<PathType, ControllerType> {
	private static final Map<Object, ValueProvider> valueProviders = new HashMap<>();
	private final RouteConfigurations<PathType, ControllerType> routeConfigurations;

	public Router(PathMatcher<PathType> pathMatcher, AbstractRoutes<PathType, ControllerType>... routes) {
		routeConfigurations = new RouteConfigurations<>(pathMatcher);

		for(AbstractRoutes r : routes) {
			r.setRouteConfigurations(routeConfigurations);
			r.configureRoutes();
		}
	}

	public ControllerType routeRequest(final RequestInfo<PathType> requestInfo) {
		RouteConfiguration<ControllerType> configuration = routeConfigurations.findRouteConfiguration(requestInfo);
		//FIXME implement sensible behavior when no configuration was found (==null)
		return configuration.executeRequest(requestInfo);
	}

	static void registerValueProvider(Object placeholderValue, ValueProvider valueProvider) {
		valueProviders.put(placeholderValue, valueProvider);
	}

	public static ValueProvider getValueProvider(final Object placeholderValue) {
		return valueProviders.get(placeholderValue);
	}
}
