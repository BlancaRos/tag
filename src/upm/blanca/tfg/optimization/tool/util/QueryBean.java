package upm.blanca.tfg.optimization.tool.util;

import java.util.Date;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class QueryBean {
	private String queryString = Constants.BLANK;
	private String queryDescription = Constants.BLANK;
	private String bbddName = Constants.BLANK;
	//private String queryType =Constants.BLANK;
	private long queryStart;
	private long queryEnd;
	private Date queryDate;

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
	//	public String getQueryType() {
	//		return queryType;
	//	}
	//	public void setQueryType(String queryType) {
	//		this.queryType = queryType;
	//	}
	public long getQueryStart() {
		return queryStart;
	}
	public void setQueryStart(long queryStart) {
		this.queryStart = queryStart;
	}
	public long getQueryEnd() {
		return queryEnd;
	}
	public void setQueryEnd(long queryEnd) {
		this.queryEnd = queryEnd;
	}
	public Date getQueryDate() {
		return queryDate;
	}
	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}
}