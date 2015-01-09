package org.autumnlibs.webmvc.jsxp;

import org.autumnlibs.webmvc.FrontController;
import org.jsxp.framework.Application;
import org.jsxp.framework.ViewAlias;

import java.util.Map;

public abstract class AbstractJsxpApplication extends Application {
	private final ViewResolver viewResolver;

	protected AbstractJsxpApplication(final FrontController<ModelAndView> frontController) {
		viewResolver = new ViewResolver(frontController);
	}

	@Override
	public ViewAlias getViewAliasFor(final String sourcePath, final String targetPath, final Map<String, Object[]> parameter) {
		return viewResolver.resolveView(sourcePath, targetPath, parameter);
	}
}
