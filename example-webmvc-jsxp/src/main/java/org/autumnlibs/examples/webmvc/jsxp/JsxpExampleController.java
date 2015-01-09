package org.autumnlibs.examples.webmvc.jsxp;

import org.autumnlibs.examples.webmvc.jsxp.views.IndexHtmlController;
import org.autumnlibs.examples.webmvc.jsxp.web.ApplicationJsxpViews;
import org.autumnlibs.webmvc.jsxp.ModelAndView;

public class JsxpExampleController {
	private ApplicationViews applicationViews = new ApplicationJsxpViews();
	public ModelAndView<Person> homePage() {
		return new ModelAndView<>(IndexHtmlController.class, new Person("John", 24));
	}
}
