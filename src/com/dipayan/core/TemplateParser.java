package com.dipayan.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParser {

	public List<Segment> parseSegments(String template) {
		List<Segment> segments = parse(template);
		return segments;
	}

	private List<Segment> parse(String template) {
		List<Segment> segments = new ArrayList<Segment>();
		int index = collectSegments(segments, template);
		addTail(segments, template, index);
		addEmptyStringIfTemplateWasEmpty(segments);
		return segments;
	}

	private int collectSegments(List<Segment> segments, String template) {
		Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");
		Matcher matcher = pattern.matcher(template);
		int index = 0;
		while (matcher.find()) {
			addPredicatePlainText(segments, template, matcher, index);
			addVariable(segments, template, matcher);
			index = matcher.end();
		}
		return index;
	}

	private void addPredicatePlainText(List<Segment> segments, String template,
			Matcher matcher, int index) {
		if (index != matcher.start()) {
			segments.add(new PlainText(template.substring(index,
					matcher.start())));
		}
	}

	private void addVariable(List<Segment> segments, String template,
			Matcher matcher) {
		segments.add(new Variable(template.substring(matcher.start() + 2,
				matcher.end() - 1)));
	}

	private void addTail(List<Segment> segments, String template, int index) {
		if (index < template.length()) {
			segments.add(new PlainText(template.substring(index)));
		}
	}

	private void addEmptyStringIfTemplateWasEmpty(List<Segment> segments) {
		if (segments.isEmpty()) {
			segments.add(new PlainText(""));
		}
	}
}
