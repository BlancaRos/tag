package upm.blanca.tfg.optimization.tool.util;

import java.io.Serializable;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class CSVReportBean implements Serializable {

	private String csv = Constants.BLANK;

	public String getCsv() {
		return csv;
	}

	public void setCsv(String csv) {
		this.csv = csv;
	}
}
