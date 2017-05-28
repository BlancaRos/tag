package upm.blanca.tfg.optimization.tool.util;

import java.io.Serializable;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class CSVRowBean implements Serializable  {

	private String csvRow = Constants.BLANK;

	public String getCsvRow() {
		return csvRow;
	}

	public void setCsvRow(String csvRow) {
		this.csvRow = csvRow;
	}
	
	
}
