package upm.blanca.tfg.optimization.tool.beans;

import java.io.Serializable;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class CSVReportBean implements Serializable {

	private static final long serialVersionUID = 776904630606334459L;
	private String csv = Constants.BLANK;
	
	/**
	 * @return the csv
	 */
	public String getCsv() {
		return csv;
	}
	/**
	 * @param csv the csv to set
	 */
	public void setCsv(String csv) {
		this.csv = csv;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}