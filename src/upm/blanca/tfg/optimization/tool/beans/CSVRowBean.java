package upm.blanca.tfg.optimization.tool.beans;

import java.io.Serializable;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class CSVRowBean implements Serializable  {

	private static final long serialVersionUID = -6651020699488034199L;
	private String csvRow = Constants.BLANK;

	/**
	 * @return the csvRow
	 */
	public String getCsvRow() {
		return csvRow;
	}

	/**
	 * @param csvRow the csvRow to set
	 */
	public void setCsvRow(String csvRow) {
		this.csvRow = csvRow;
	}

	
}