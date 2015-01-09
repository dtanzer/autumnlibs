package org.autumnlibs.examples.webmvc.jsxp;

import org.autumnlibs.webmvc.FrontController;
import org.autumnlibs.webmvc.jsxp.AbstractJsxpApplication;

public class JsxpApplication extends AbstractJsxpApplication {
	public JsxpApplication() {
		super(new FrontController<>(new JsxpExampleRoutes()));
	}
}
