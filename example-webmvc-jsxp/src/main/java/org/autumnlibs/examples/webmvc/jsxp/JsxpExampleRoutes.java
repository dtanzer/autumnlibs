package org.autumnlibs.examples.webmvc.jsxp;

import org.autumnlibs.webmvc.jsxp.ModelAndView;

public class JsxpExampleRoutes extends org.autumnlibs.router.AbstractRoutes<String,ModelAndView> {
	private JsxpExampleController jsxpExampleController = new JsxpExampleController();
	protected JsxpExampleRoutes() {
		super(ModelAndView.class);
	}

	@Override
	protected void configureRoutes() {
		route("index.html").to(jsxpExampleController).homePage();
	}
}
