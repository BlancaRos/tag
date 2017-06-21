package upm.blanca.tfg.optimization.tool.beans;

import java.io.Serializable;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class ReportBean implements Serializable {


	private static final long serialVersionUID = 627774797785665696L;
	private String query = Constants.BLANK;
	private String bbdd = Constants.BLANK;
	private String avgTime = Constants.BLANK;
	private String rows;
	
	public ReportBean() {
		
	}
	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}
	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	/**
	 * @return the bbdd
	 */
	public String getBbdd() {
		return bbdd;
	}
	/**
	 * @param bbdd the bbdd to set
	 */
	public void setBbdd(String bbdd) {
		this.bbdd = bbdd;
	}
	/**
	 * @return the avgTime
	 */
	public String getAvgTime() {
		return avgTime;
	}
	/**
	 * @param avgTime the avgTime to set
	 */
	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}
	/**
	 * @return the rows
	 */
	public String getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(String rows) {
		this.rows = rows;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
