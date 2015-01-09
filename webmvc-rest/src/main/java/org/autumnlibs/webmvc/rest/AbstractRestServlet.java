package org.autumnlibs.webmvc.rest;

import org.autumnlibs.valueproviders.servlet.ServletValueProviders;
import org.autumnlibs.webmvc.FrontController;
import org.autumnlibs.webmvc.rest.renderer.ContentNegotiatingRestDataRenderer;
import org.autumnlibs.webmvc.rest.renderer.RestDataRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRestServlet extends HttpServlet {
	private final FrontController<Object> frontController;
	private final RestDataRenderer restDataRenderer;

	public AbstractRestServlet(final FrontController<Object> frontController) {
		this(frontController, new ContentNegotiatingRestDataRenderer());
	}

	public AbstractRestServlet(final FrontController<Object> frontController, final RestDataRenderer restDataRenderer) {
		this.frontController = frontController;
		this.restDataRenderer = restDataRenderer;
	}

	@Override
	protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> additionalInfo = new HashMap<>();
		ServletValueProviders.registerValues(additionalInfo, req, resp);
		Object viewData = frontController.dispatchRequest(req.getPathInfo(), additionalInfo);
		restDataRenderer.render(viewData, req, resp);
	}
}
