package org.autumnlibs.examples.webmvc.rest;

public class Person {
	private String name;
	private int age;

	public Person(final String name, final int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}
