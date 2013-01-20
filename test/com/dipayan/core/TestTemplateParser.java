package com.dipayan.core;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestTemplateParser {

	@Test
	public void emptyTemplateRenderAsEmptyString() {
		List<Segment> segments = parse("");
		assertSegments(segments, new PlainText(""));
	}

	@Test
	public void templateWithOnlyPlainText() {
		List<Segment> segments = parse("only plain text");
		assertSegments(segments, new PlainText("only plain text"));
	}

	private List<Segment> parse(String template) {
		return new TemplateParser().parseSegments(template);
	}

	private void assertSegments(List<? extends Object> actual,
			Object... expected) {
		assertEquals("Number of segments does not match", expected.length,
				actual.size());
		assertEquals(Arrays.asList(expected), actual);
	}

	@Test
	public void parsingTemplateIntoSegmentObjects() throws Exception {
		TemplateParser parser = new TemplateParser();
		List<Segment> segments = parser.parseSegments("a ${b} c ${d}");
		assertSegments(segments, new PlainText("a "), new Variable("b"),
				new PlainText(" c "), new Variable("d"));
	}

}
