package org.autumnlibs.webmvc.rest.renderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RestDataRenderer {
	void render(Object viewData, HttpServletRequest req, HttpServletResponse resp);
}
