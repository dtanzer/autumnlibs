package org.autumnlibs.router;

public abstract class ValueProvider<R, T> {
	private T placeholderValue;

	protected ValueProvider(final PlaceholderValueFactory<T> placeholderValueFactory) {
		placeholderValue = placeholderValueFactory.createPlaceholderValue();
		Router.registerValueProvider(placeholderValue, this);
	}

	protected abstract T getValue(RequestInfo<R> requestInfo);

	protected final T getPlaceholderValue() {
		return placeholderValue;
	}
}
