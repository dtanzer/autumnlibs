package org.autumnlibs.router.providers;

import org.autumnlibs.router.RequestInfo;
import org.autumnlibs.router.ValueProvider;

public class RequestInfoProvider<T> extends ValueProvider<T, RequestInfo> {
	protected RequestInfoProvider() {
		super(() -> new RequestInfo());
	}

	@Override
	protected RequestInfo<T> getValue(final RequestInfo<T> requestInfo) {
		return requestInfo;
	}

	public static <T> RequestInfo<T> requestInfo() {
		return new RequestInfoProvider<>().getPlaceholderValue();
	}
}
