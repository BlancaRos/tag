package upm.blanca.tfg.optimization.tool.util;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class QueryBean {
	private String queryString = Constants.BLANK;
	private String queryDescription = Constants.BLANK;
	private String bbddService = Constants.BLANK;
	private String bbddName = Constants.BLANK;
	private String bbddUser = Constants.BLANK;
	private String bbddPass = Constants.BLANK;
	private String queryStartTime;
	private String queryEndTime;
	private int totalTime;
	private String queryDate;
	private int numRows;
	private String costQuery;
	private String avgTime;
	private int idBBDD;
	private byte[] csv;

	public byte[] getCsv() {
		return csv;
	}
	public void setCsv(byte[] csv) {
		this.csv = csv;
	}
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
	public String getBbddService() {
		return bbddService;
	}
	public void setBbddService(String bbddName) {
		this.bbddService = bbddName;
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
	public String getBbddUser() {
		return bbddUser;
	}
	public void setBbddUser(String bbddUser) {
		this.bbddUser = bbddUser;
	}
	public String getBbddPass() {
		return bbddPass;
	}
	public void setBbddPass(String bbddPass) {
		this.bbddPass = bbddPass;
	}
	public int getIdBBDD() {
		return idBBDD;
	}
	public void setIdBBDD(int idBBDD) {
		this.idBBDD = idBBDD;
	}
	public String getBbddName() {
		return bbddName;
	}
	public void setBbddName(String bbddName) {
		this.bbddName = bbddName;
	}
}