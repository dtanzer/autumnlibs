package org.autumnlibs.examples.webmvc.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestController {
	private List<Person> persons = new ArrayList<>(Arrays.asList(new Person("John", 27), new Person("Marcus", 32)));

	public List<Person> persons() {
		return persons;
	}

	public void createPerson(final String name, final int age) {
		persons.add(new Person(name, age));
	}
}
