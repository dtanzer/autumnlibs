package org.autumnlibs.router.providers;

import org.autumnlibs.router.RequestInfo;
import org.autumnlibs.router.ValueProvider;

public class RequestPathProvider<T> extends ValueProvider<T, T> {
	protected RequestPathProvider(final Class<T> valueType) {
		super(() -> {
			try {
				return valueType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new IllegalStateException("Could not instantiate value type", e);
			}
		});
	}

	@Override
	protected T getValue(final RequestInfo<T> requestInfo) {
		return requestInfo.path();
	}

	public static <T> T requestPath(final Class<T> resolverResultClass) {
		return new RequestPathProvider<>(resolverResultClass).getPlaceholderValue();
	}
}
