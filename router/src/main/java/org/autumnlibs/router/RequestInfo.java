package org.autumnlibs.router;

import java.util.HashMap;
import java.util.Map;

public class RequestInfo<P> {
	private final P path;
	private final Map<String, Object> additionalInfo;

	public RequestInfo() {
		this(null);
	}

	public RequestInfo(final P path) {
		this(path, new HashMap<>());
	}

	public RequestInfo(final P path, final Map<String, Object> additionalInfo) {
		this.path = path;
		this.additionalInfo = additionalInfo;
	}

	public P path() {
		return path;
	}

	public Map<String, Object> additionalInfo() {
		return additionalInfo;
	}
}
