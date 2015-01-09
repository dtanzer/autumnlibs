package org.autumnlibs.examples.webmvc.jsxp.web;

import org.autumnlibs.examples.webmvc.jsxp.ApplicationViews;
import org.autumnlibs.examples.webmvc.jsxp.views.IndexHtmlController;
import org.jsxp.framework.ViewController;

public class ApplicationJsxpViews implements ApplicationViews {
	@Override
	public Class<? extends ViewController> indexHtmlView() {
		return IndexHtmlController.class;
	}
}
