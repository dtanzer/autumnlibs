package org.autumnlibs.examples.webmvc.rest;

import org.autumnlibs.webmvc.FrontController;
import org.autumnlibs.webmvc.rest.AbstractRestServlet;

public class RestServlet extends AbstractRestServlet {
	public RestServlet() {
		super(new FrontController<>(new RestExampleRoutes()));
	}

}
