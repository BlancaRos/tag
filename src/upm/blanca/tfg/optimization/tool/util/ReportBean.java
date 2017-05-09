package upm.blanca.tfg.optimization.tool.util;

import java.io.Serializable;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class ReportBean implements Serializable {

	private String query = Constants.BLANK;
	private String avgTime = Constants.BLANK;
	private String rows;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	
	/**
	 * añadir datos cuando mysql
	 **/
	
	
}
