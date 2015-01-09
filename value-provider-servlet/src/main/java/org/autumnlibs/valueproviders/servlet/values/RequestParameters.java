package org.autumnlibs.valueproviders.servlet.values;

import org.autumnlibs.router.PlaceholderValueFactory;
import org.autumnlibs.router.RequestInfo;
import org.autumnlibs.router.ValueProvider;
import org.autumnlibs.valueproviders.servlet.ServletValueProviders;

import javax.servlet.http.HttpServletRequest;

public class RequestParameters<T> extends ValueProvider<String, T> {
	private final Class<T> parameterType;
	private String parameterName;

	public RequestParameters(final String parameterName, final Class<T> parameterType, PlaceholderValueFactory placeholderValueFactory) {
		super(placeholderValueFactory);
		this.parameterType = parameterType;
		this.parameterName = parameterName;
	}

	public static String stringParameter(String parameterName) {
		return requestParameter(parameterName, String.class, () -> new String());
	}

	public static Integer intParameter(String parameterName) {
		return requestParameter(parameterName, Integer.class, () -> new Integer(0));
	}

	private static <T> T requestParameter(final String parameterName, final Class<T> parameterType, final PlaceholderValueFactory valueFactory) {
		return new RequestParameters<>(parameterName, parameterType, valueFactory).getPlaceholderValue();
	}

	@Override
	protected T getValue(final RequestInfo<String> requestInfo) {
		HttpServletRequest request = ServletValueProviders.getRequest(requestInfo.additionalInfo());

		String parameter = request.getParameter(parameterName);

		if(Integer.class.equals(parameterType)) {
			return (T) Integer.valueOf(parameter);
		} else if(String.class.equals(parameterType)) {
			return (T) parameter;
		}

		throw new IllegalStateException("Unknown parameter type: "+parameterType);
	}
}
