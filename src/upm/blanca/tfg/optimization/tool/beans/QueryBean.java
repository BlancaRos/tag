package upm.blanca.tfg.optimization.tool.beans;

import java.util.List;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class QueryBean {
	private String queryString = Constants.BLANK;
	private String queryDescription = Constants.BLANK;
	private String bbddService = Constants.BLANK;
	private String bbddName = Constants.BLANK;
	private String bbddUser = Constants.BLANK;
	private String bbddPass = Constants.BLANK;
	private List<ExecutionBean> executionBeanList;
	private String avgTime;
	private int numRows;
	private int idBBDD;
	private byte[] csv;
	private List<CSVRowBean> stringCSV;
	
	
	public QueryBean() {
		
	}
	
	/**
	 * @return the queryString
	 */
	public String getQueryString() {
		return queryString;
	}
	/**
	 * @param queryString the queryString to set
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	/**
	 * @return the queryDescription
	 */
	public String getQueryDescription() {
		return queryDescription;
	}
	/**
	 * @param queryDescription the queryDescription to set
	 */
	public void setQueryDescription(String queryDescription) {
		this.queryDescription = queryDescription;
	}
	/**
	 * @return the bbddService
	 */
	public String getBbddService() {
		return bbddService;
	}
	/**
	 * @param bbddService the bbddService to set
	 */
	public void setBbddService(String bbddService) {
		this.bbddService = bbddService;
	}
	/**
	 * @return the bbddName
	 */
	public String getBbddName() {
		return bbddName;
	}
	/**
	 * @param bbddName the bbddName to set
	 */
	public void setBbddName(String bbddName) {
		this.bbddName = bbddName;
	}
	/**
	 * @return the bbddUser
	 */
	public String getBbddUser() {
		return bbddUser;
	}
	/**
	 * @param bbddUser the bbddUser to set
	 */
	public void setBbddUser(String bbddUser) {
		this.bbddUser = bbddUser;
	}
	/**
	 * @return the bbddPass
	 */
	public String getBbddPass() {
		return bbddPass;
	}
	/**
	 * @param bbddPass the bbddPass to set
	 */
	public void setBbddPass(String bbddPass) {
		this.bbddPass = bbddPass;
	}
	/**
	 * @return the executionBeanList
	 */
	public List<ExecutionBean> getExecutionBeanList() {
		return executionBeanList;
	}
	/**
	 * @param executionBeanList the executionBeanList to set
	 */
	public void setExecutionBeanList(List<ExecutionBean> executionBeanList) {
		this.executionBeanList = executionBeanList;
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
	 * @return the numRows
	 */
	public int getNumRows() {
		return numRows;
	}
	/**
	 * @param numRows the numRows to set
	 */
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
	/**
	 * @return the idBBDD
	 */
	public int getIdBBDD() {
		return idBBDD;
	}
	/**
	 * @param idBBDD the idBBDD to set
	 */
	public void setIdBBDD(int idBBDD) {
		this.idBBDD = idBBDD;
	}
	/**
	 * @return the csv
	 */
	public byte[] getCsv() {
		return csv;
	}
	/**
	 * @param csv the csv to set
	 */
	public void setCsv(byte[] csv) {
		this.csv = csv;
	}
	/**
	 * @return the stringCSV
	 */
	public List<CSVRowBean> getStringCSV() {
		return stringCSV;
	}
	/**
	 * @param stringCSV the stringCSV to set
	 */
	public void setStringCSV(List<CSVRowBean> stringCSV) {
		this.stringCSV = stringCSV;
	}

	
}