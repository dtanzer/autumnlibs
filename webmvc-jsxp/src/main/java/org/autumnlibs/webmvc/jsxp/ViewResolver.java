package org.autumnlibs.webmvc.jsxp;

import org.autumnlibs.webmvc.FrontController;
import org.jsxp.framework.ViewAlias;
import org.jsxp.framework.ViewController;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ViewResolver {
	private FrontController<ModelAndView> frontController;

	public ViewResolver(final FrontController<ModelAndView> frontController) {
		this.frontController = frontController;
	}

	public ViewAlias resolveView(final String sourcePath, final String targetPath, final Map<String, Object[]> parameters) {
		ModelAndView modelAndView = dispatchRequest(targetPath);
		ViewController view = getViewController(modelAndView);
		return new ViewAlias(view, parameters);
	}

	private ModelAndView dispatchRequest(final String targetPath) {
		Map<String, Object> additionalInfo = new HashMap<>();
		return frontController.dispatchRequest(targetPath, additionalInfo);
	}

	private ViewController getViewController(final ModelAndView modelAndView) {
		Class<ViewController> viewClass = modelAndView.view();
		Constructor<?>[] constructors = viewClass.getConstructors();
		ViewController view = null;
		try {
			for(Constructor c : constructors) {
				if(c.getParameterTypes().length==1 && c.getParameterTypes()[0].isAssignableFrom(modelAndView.model().getClass())) {
					view = (ViewController) c.newInstance(modelAndView.model());
				}
			}
			if(view == null) {
				view = viewClass.newInstance();
			}
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException("Could not instantiate view controller", e);
		}
		return view;
	}
}
