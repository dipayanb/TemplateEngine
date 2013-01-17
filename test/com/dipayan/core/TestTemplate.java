package com.dipayan.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TestTemplate {

	private Template template;

	@Before
	public void setUp() {
		template = new Template("${one}, ${two}, ${three}");
		template.set("one", "1");
		template.set("two", "2");
		template.set("three", "3");
	}

	@Test
	public void multipleVariables() throws Exception {
		assertTemplateEvaluatesTo("1, 2, 3");
	}

	@Test
	public void unknownVariablesAreIgnored() throws Exception {
		template.set("name", "Sanchari");
		template.set("unknown", "Unknown");
		assertTemplateEvaluatesTo("1, 2, 3");
	}

	private void assertTemplateEvaluatesTo(String expected) throws Exception {
		assertEquals(expected, template.evaluate());
	}
	
	@Test
	public void missingValueRaisesException() {
		try {
			new Template("${foo}").evaluate();
			fail("evaluate() should throw an exception if a variable was left without a value!");
		} catch (MissingValueException ex) {
			assertEquals("No value for ${foo}", ex.getMessage());
		}
	}
	
	@Test
    public void variablesGetProcessedJustOnce() throws Exception {
        template.set("one", "${one}");
        template.set("two", "${three}");
        template.set("three", "${two}");
        assertTemplateEvaluatesTo("${one}, ${three}, ${two}");
	}

}
