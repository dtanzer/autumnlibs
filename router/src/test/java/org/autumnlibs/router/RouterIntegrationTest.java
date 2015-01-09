package org.autumnlibs.router;

import org.autumnlibs.router.providers.RequestInfoProvider;
import org.autumnlibs.router.providers.RequestPathProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RouterIntegrationTest {
	private Router<String, Object> router;
	private TestController testController;

	@Before
	public void setup() {
		testController = new TestController();

		router = new Router<>((toResolve, configuredPath) -> toResolve.equals(configuredPath), new TestRoutes());
	}

	@Test
	public void routesSimpleRoute() {
		Object routeResult = router.routeRequest(new RequestInfo("/simple"));

		assertEquals("result of simple route", routeResult);
	}

	@Test
	public void routesSimpleRouteWithStaticArgument() {
		Object routeResult = router.routeRequest(new RequestInfo<>("/simple/withStaticArg"));

		assertEquals("static argument", routeResult);
	}

	@Test
	public void routesSimpleRouteWithResolvedArgumentRequestPath() {
		Object routeResult = router.routeRequest(new RequestInfo<>("/simple/withArgPath"));

		assertEquals("/simple/withArgPath", routeResult);
	}

	@Test
	public void routesSimpleRouteWithResolvedArgumentRequestInfo() {
		Object routeResult = router.routeRequest(new RequestInfo<>("/simple/withArgInfo"));

		assertTrue(routeResult instanceof RequestInfo);
		assertEquals("/simple/withArgInfo", ((RequestInfo<String>) routeResult).path());
	}

	public static class TestController {
		Object simpleRoute() {
			return "result of simple route";
		}

		Object simpleRouteWithArgument(Object argument) {
			return argument;
		}
	}

	private class TestRoutes extends AbstractRoutes<String, Object> {
		protected TestRoutes() {
			super(Object.class);
		}

		@Override
		protected void configureRoutes() {
			route("/simple").to(testController).simpleRoute();
			route("/simple/withStaticArg").to(testController).simpleRouteWithArgument("static argument");
			route("/simple/withArgPath").to(testController).simpleRouteWithArgument(RequestPathProvider.requestPath(String.class));
			route("/simple/withArgInfo").to(testController).simpleRouteWithArgument(RequestInfoProvider.requestInfo());
		}
	}
}
