package com.dipayan.core;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TestVariableSegment {
	Map<String, String> variables;
	
	@Before
	public void setUp() {
		variables = new HashMap<String, String>();
	}
	@Test
	public void variableEvaluatesToItsValue() {
		String name = "myVar";
		String value = "myValue";
		variables.put(name, value);
		assertEquals(value, new Variable(name).evaluate(variables));
	}
	
	@Test(expected=MissingValueException.class)
	public void missingVariableRaisedException() {
		String name = "myvar";
		new Variable(name).evaluate(variables);
	}

}
