package org.autumnlibs.router;

public abstract class RouteMatcher<PathType> {
	protected abstract boolean matches(RequestInfo<PathType> requestInfo);

	public static <P> RouteMatcher<P> matchesAll() {
		return new RouteMatcher<P>() {
			@Override
			protected boolean matches(final RequestInfo<P> requestInfo) {
				return true;
			}
		};
	}

	public static <P> RouteMatcher<P> matches(final RouteMatcher<P>... matchers) {
		return new RouteMatcher<P>() {
			@Override
			protected boolean matches(final RequestInfo<P> requestInfo) {
				boolean isMatch = true;
				for(RouteMatcher<P> m : matchers) {
					isMatch = isMatch && m.matches(requestInfo);
				}
				return isMatch;
			}
		};
	}
}
