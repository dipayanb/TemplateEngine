package com.dipayan.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestTemplate {

	@Test
	public void oneVariable() throws Exception {
		Template template = new Template("Hello, ${name}");
		template.set("name", "Reader");
		assertEquals("Hello, Reader", template.evaluate());
	}
	
	@Test
	public void differentVariable() throws Exception {
		Template template = new Template("Hello, ${name}");
		template.set("name", "Dipayan");
		assertEquals("Hello, Dipayan", template.evaluate());
	}
	
	@Test
	public void differentTemplate() throws Exception {
		Template template = new Template("Hi, ${name}");
		template.set("name", "Sanchari");
		assertEquals("Hi, Sanchari", template.evaluate());
	}
	
	@Test
	public void multipleVariables() throws Exception {
		Template template = new Template("${one}, ${two}, ${three}");
		template.set("one", "1");
		template.set("two", "2");
		template.set("three", "3");
		assertEquals("1, 2, 3", template.evaluate());
	}
	
	@Test
	public void unknownVariablesAreIgnored() throws Exception {
		Template template = new Template("Hi, ${name}");
		template.set("name", "Sanchari");
		template.set("unknown", "Unknown");
		assertEquals("Hi, Sanchari", template.evaluate());
	}

}
