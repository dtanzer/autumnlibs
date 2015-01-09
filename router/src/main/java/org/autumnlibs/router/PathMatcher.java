package org.autumnlibs.router;

public interface PathMatcher<PathType> {
	boolean matchesPath(final PathType pathToResolve, PathType configuredPath);
	default void enhanceRequestInfo(RequestInfo<PathType> requestInfo) {
	}
}
