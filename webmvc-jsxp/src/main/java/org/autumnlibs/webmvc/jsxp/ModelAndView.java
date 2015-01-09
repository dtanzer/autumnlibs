package org.autumnlibs.webmvc.jsxp;

import org.jsxp.framework.ViewController;

public class ModelAndView<ModelType> {
	private Class<? extends ViewController> view;
	private ModelType model;

	public ModelAndView(final Class<? extends ViewController> view, final ModelType model) {
		this.view = view;
		this.model = model;
	}

	public Class<? extends ViewController> view() {
		return view;
	}

	public ModelType model() {
		return model;
	}
}
