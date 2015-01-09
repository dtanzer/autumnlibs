package org.autumnlibs.webmvc.rest.renderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContentNegotiatingRestDataRenderer implements RestDataRenderer {
	//FIXME make child renderers configurable
	private JsonDataRenderer jsonDataRenderer = new JsonDataRenderer();

	@Override
	public void render(final Object viewData, final HttpServletRequest req, final HttpServletResponse resp) {
		//FIXME do the actual content negotiation
		jsonDataRenderer.render(viewData, req, resp);
	}
}
