package org.autumnlibs.examples.webmvc.jsxp.generated;

import org.jsxp.framework.ViewController;

public class IndexHtmlControllerGenerated extends ViewController {
	public IndexHtmlControllerGenerated() {
		super("index.html");
	}


	@org.jsxp.framework.annotations.VariableSetter(name="name")
	public void setVariableName(String value) {
		replaceVariableValue("name", value);
	}

	@org.jsxp.framework.annotations.VariableSetter(name="age")
	public void setVariableAge(String value) {
		replaceVariableValue("age", value);
	}

}
