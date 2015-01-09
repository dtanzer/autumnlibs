package org.autumnlibs.examples.webmvc.jsxp.views;

import org.autumnlibs.examples.webmvc.jsxp.Person;
import org.autumnlibs.examples.webmvc.jsxp.generated.IndexHtmlControllerGenerated;

public class IndexHtmlController extends IndexHtmlControllerGenerated {
	private final Person person;

	public IndexHtmlController(Person person) {
		this.person = person;
	}

	@Override
	public void execute() throws Exception {
		setVariableName(person.getName());
		setVariableAge(String.valueOf(person.getAge()));
	}
}
