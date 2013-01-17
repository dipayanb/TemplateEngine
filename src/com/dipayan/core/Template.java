package com.dipayan.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
		String result = template;
		for(Entry<String, String> entry : variables.entrySet()) {
			String regex = "\\$\\{" + entry.getKey() + "\\}";
			result = result.replaceAll(regex, entry.getValue());
		}
		return result;
	}

}
