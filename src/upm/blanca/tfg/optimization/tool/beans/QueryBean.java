package upm.blanca.tfg.optimization.tool.beans;

import java.util.List;

import upm.blanca.tfg.optimization.tool.constants.Constants;
import upm.blanca.tfg.optimization.tool.interfaz.util.ExecutionBean;

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
	public String getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
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
	public List<ExecutionBean> getExecutionBeanList() {
		return executionBeanList;
	}
	public void setExecutionBeanList(List<ExecutionBean> executionBeanList) {
		this.executionBeanList = executionBeanList;
	}
	public int getNumRows() {
		return numRows;
	}
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
	public List<CSVRowBean> getStringCSV() {
		return stringCSV;
	}
	public void setStringCSV(List<CSVRowBean> stringCSV) {
		this.stringCSV = stringCSV;
	}
}