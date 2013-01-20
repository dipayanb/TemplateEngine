package com.dipayan.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {

	private Map<String, String> variables;
	private String template;

	public Template(String template) {
		this.template = template;
		this.variables = new HashMap<String, String>();
	}

	public void set(String variable, String value) {
		variables.put(variable, value);
	}

	public String evaluate() {
		TemplateParser parser = new TemplateParser();
		List<Segment> segments = parser.parseSegments(template);
		return concatenate(segments);
	}

	private String concatenate(List<Segment> segments) {
		StringBuilder result = new StringBuilder();
		for(Segment segment : segments) {
			result.append(segment.evaluate(variables));
		}
		return result.toString();
	}

}
