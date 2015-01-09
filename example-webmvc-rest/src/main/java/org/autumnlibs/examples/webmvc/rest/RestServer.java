package org.autumnlibs.examples.webmvc.rest;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletInfo;

import javax.servlet.ServletException;

import static io.undertow.servlet.Servlets.defaultContainer;
import static io.undertow.servlet.Servlets.deployment;
import static io.undertow.servlet.Servlets.servlet;

public class RestServer {
	private static final String MYAPP = "/rest";

	public static void main(final String[] args) {
		startUndertow(servlet("RestServlet", RestServlet.class).addMapping("/*"));
	}

	private static void startUndertow(final ServletInfo servlet) {
		try {

			DeploymentInfo servletBuilder = deployment()
					.setClassLoader(RestServer.class.getClassLoader())
					.setContextPath(MYAPP)
					.setDeploymentName("test.war")
					.addServlets(servlet);

			DeploymentManager manager = defaultContainer().addDeployment(servletBuilder);
			manager.deploy();

			HttpHandler servletHandler = manager.start();
			PathHandler path = Handlers.path(Handlers.redirect(MYAPP))
					.addPrefixPath(MYAPP, servletHandler);
			Undertow server = Undertow.builder()
					.addHttpListener(8080, "localhost")
					.setHandler(path)
					.build();
			server.start();
		} catch (ServletException e) {
			throw new RuntimeException(e);
		}
	}
}
