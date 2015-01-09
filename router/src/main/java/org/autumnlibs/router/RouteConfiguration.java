package org.autumnlibs.router;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class RouteConfiguration<T> {
	private Object controllerObject;
	private Method controllerMethod;
	private Object[] controllerMethodArgs;
	private Class<T> routeResultClass;

	private RouteConfiguration(final Class<T> routeResultClass) {
		this.routeResultClass = routeResultClass;
	}

	public <CT> CT to(CT controllerObject) {
		this.controllerObject = controllerObject;
		return spy(controllerObject);
	}

	private <CT> CT spy(final CT controllerObject) {
		ProxyFactory factory = new ProxyFactory();
		factory.setSuperclass(controllerObject.getClass());

		MethodHandler handler = (self, thisMethod, proceed, args) -> handleSpiedMethod(self, thisMethod, proceed, args);

		try {
			return (CT) factory.create(new Class<?>[0], new Object[0], handler);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
	}

	private Object handleSpiedMethod(final Object self, final Method thisMethod, final Method proceed, final Object[] args) {
		if(!(Void.class.equals(thisMethod.getReturnType()) || void.class.equals(thisMethod.getReturnType()) || routeResultClass.isAssignableFrom(thisMethod.getReturnType()))) {
			throw new IllegalArgumentException("Method \""+thisMethod.getName()+"\" has incompatible return type \""+thisMethod.getReturnType()+"\" (expected: \""+routeResultClass+"\").");
		}
		if(controllerMethod == null) {
			controllerMethod = thisMethod;
			controllerMethodArgs = args;
		} else {
			throw new IllegalStateException("Only one controller method can be bound.");
		}

		return null;
	}

	static <T> RouteConfiguration<T> forResultClass(final Class<T> routeResultClass) {
		return new RouteConfiguration<>(routeResultClass);
	}

	T executeRequest(final RequestInfo requestInfo) {
		Object[] actualMethodArgs = compileMethodArgs(requestInfo);
		try {
			return (T) controllerMethod.invoke(controllerObject, actualMethodArgs);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException("Could not invoke controller method", e);
		}
	}

	private Object[] compileMethodArgs(final RequestInfo requestInfo) {
		Object[] methodArgs = new Object[controllerMethodArgs.length];
		for(int i=0; i<controllerMethodArgs.length; i++) {
			Object arg = controllerMethodArgs[i];

			ValueProvider valueProvider = Router.getValueProvider(arg);
			if(valueProvider != null) {
				methodArgs[i] = valueProvider.getValue(requestInfo);
			} else {
				methodArgs[i] = controllerMethodArgs[i];
			}
		}
		return methodArgs;
	}
}
