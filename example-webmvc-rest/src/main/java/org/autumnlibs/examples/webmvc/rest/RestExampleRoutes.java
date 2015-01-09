package org.autumnlibs.examples.webmvc.rest;

import org.autumnlibs.router.AbstractRoutes;
import org.autumnlibs.valueproviders.servlet.matchers.HttpMethod;

import static org.autumnlibs.router.RouteMatcher.matches;
import static org.autumnlibs.valueproviders.servlet.ServletValueProviders.method;
import static org.autumnlibs.valueproviders.servlet.values.RequestParameters.intParameter;
import static org.autumnlibs.valueproviders.servlet.values.RequestParameters.stringParameter;

public class RestExampleRoutes extends AbstractRoutes<String, Object> {
	private final RestController restController;

	protected RestExampleRoutes() {
		super(Object.class);

		restController = new RestController();
	}

	@Override
	protected void configureRoutes() {
		route("/persons", matches(method(HttpMethod.GET))).to(restController).persons();
		route("/persons", matches(method(HttpMethod.POST))).to(restController)
				.createPerson(stringParameter("name"), intParameter("age"));
	}
}
