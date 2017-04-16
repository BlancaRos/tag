package upm.blanca.tfg.optimization.tool.util;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class QueryBean {
	private String queryString = Constants.BLANK;
	private String queryDescription = Constants.BLANK;
	private String bbddName = Constants.BLANK;
	private String queryStartTime;
	private String queryEndTime;
	private int totalTime;
	private String queryDate;
	private int numRows;
	private String costQuery;
	private String avgTime;

	public QueryBean(){
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public String getQueryDescription() {
		return queryDescription;
	}
	public void setQueryDescription(String queryDescription) {
		this.queryDescription = queryDescription;
	}
	public String getBbddName() {
		return bbddName;
	}
	public void setBbddName(String bbddName) {
		this.bbddName = bbddName;
	}
	public String getQueryStartTime() {
		return queryStartTime;
	}
	public String getQueryDate() {
		return queryDate;
	}
	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}
	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}
	public String getQueryEndTime() {
		return queryEndTime;
	}
	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}
	public int getNumRows() {
		return numRows;
	}
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
	public String getCostQuery() {
		return costQuery;
	}
	public void setCostQuery(String costQuery) {
		this.costQuery = costQuery;
	}
	public String getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
}