package org.autumnlibs.webmvc.rest.renderer;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonDataRenderer implements RestDataRenderer {
	@Override
	public void render(final Object viewData, final HttpServletRequest req, final HttpServletResponse resp) {
		Gson gson = new Gson();

		try {
			gson.toJson(viewData, resp.getWriter());
		} catch (IOException e) {
			throw new IllegalStateException("Could not write JSON", e);
		}
	}
}
