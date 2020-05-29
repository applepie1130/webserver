
package com.webserver.model.type;

public enum HtmlFileType {
	
	INDEX("index.html"),
	ERROR_404("error404.html"),
	ERROR_501("error501.html")
	;
	
	private String name;
	
	private HtmlFileType(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}