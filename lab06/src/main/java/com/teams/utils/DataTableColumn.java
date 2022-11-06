package com.teams.utils;


/*
 * Used when rendering the dataView.xhtml template.
 * [property] field describes the actual name of the property of the object that is represented on one of the rows of the table.
 * [header] is the string that we want to render as the column heading.
 * e.g. Each City object has a property [name]. We initialize an object DataTableColumn("name", "Name");
 * in dataView.xhtml, we retrieve the [name] property from City, but we show "Name" as the header of the column.
 */
public class DataTableColumn {
	private String property;
	private String header;
	
	public DataTableColumn(String property, String header) {
		this.setProperty(property);
		this.setHeader(header);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
}
