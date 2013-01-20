package com.dipayan.core;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestTemplatePerformance {

	Template template = null;

	@Before
	public void setUp() throws Exception {
		// Creating a template containing 100 words with 20 variables and 15 character value
		StringBuilder sb = new StringBuilder();
		List<String> variables = new ArrayList<String>();
		
		for(int i = 0; i < 400; i++) {
			sb.append("This is line number ${line" + (i+1) + "}.\n");
			variables.add("line" + (i+1));
		}
		
		template = new Template(sb.toString());
		
		int count = 0;
		Iterator<String> itr = variables.iterator();
		while(itr.hasNext()) {
			String variable = itr.next();
			count++;
			template.set(variable, String.valueOf(count));
		}
	}

	@Test
	public void templateWith100WordsAnd20Variables() {
		long expected = 10L;
		long time = System.currentTimeMillis();
		template.evaluate();
		time = System.currentTimeMillis() - time;
		assertTrue("Rendering the template took " + time
		        + "ms while the target was " + expected + "ms",
		        time <= expected);
	}

}
