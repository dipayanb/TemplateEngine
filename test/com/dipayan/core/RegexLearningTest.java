package com.dipayan.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegexLearningTest {

	@Test
	public void howGroupCountWorks() throws Exception{
		String haystack = "The needle shop sells needles";
		String regex = "(needle)";
		Matcher matcher = Pattern.compile(regex).matcher(haystack);
		assertFalse("Group count is not 4", 2 == matcher.groupCount());
	}
	
	@Test
	public void testFindStartAndEnd() throws Exception {
		String haystack = "The needle shop sells needles";
		String regex = "(needle)";
		Matcher matcher = Pattern.compile(regex).matcher(haystack);
		
		assertTrue(matcher.find());
		assertEquals("Wrong start index of 1st match", 4, matcher.start());
		assertEquals("Wrong end index of 1st match", 10, matcher.end());
		
		assertTrue(matcher.find());
		assertEquals("Wrong start index of 1st match", 22, matcher.start());
		assertEquals("Wrong end index of 1st match", 28, matcher.end());
		
		assertFalse(matcher.find());
	}
}
