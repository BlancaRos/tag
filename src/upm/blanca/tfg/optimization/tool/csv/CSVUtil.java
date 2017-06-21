package upm.blanca.tfg.optimization.tool.csv;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import upm.blanca.tfg.optimization.tool.beans.CSVRowBean;
import upm.blanca.tfg.optimization.tool.constants.Constants;

public class CSVUtil {

	private static final char DEFAULT_SEPARATOR = ',';

	/**
	 * Metodo para generar el bean con la inforamcion de la fila obtenida de BBDD e ir generando el csv
	 * @param w - writer
	 * @param values - datos de la fila
	 * @param separators - separador
	 * @return CSVRowBean Bean con la informacion de la fila para el csv
	 * @throws IOException ioe
	 */
	public static CSVRowBean writeLine(Writer w, List<String> values, char separators) throws IOException {
		CSVRowBean csvRow = new CSVRowBean();
		boolean first = true;

		if (separators == ' ') {
			separators = DEFAULT_SEPARATOR;
		}

		StringBuilder sb = new StringBuilder();
		for (String value : values) {
			if (!first) {
				sb.append(separators);
			}

			if(value == null || "null".equalsIgnoreCase(value)){
				sb.append(Constants.BLANK);
			} else{
				sb.append(value);
			}
			first = false;
		}
		sb.append(Constants.LINE_BREAK);
		w.append(sb.toString());
		csvRow.setCsvRow(sb.toString());
		return csvRow;
	}
}