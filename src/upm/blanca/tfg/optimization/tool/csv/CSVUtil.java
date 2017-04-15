package upm.blanca.tfg.optimization.tool.csv;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import upm.blanca.tfg.optimization.tool.constants.Constants;

public class CSVUtil {

	private static final char DEFAULT_SEPARATOR = ',';


//	private static String followCVSformat(String value) {
//
//		//Escapar comillas
//		String result = value;
//		if (result.contains("\"")) {
//			result = result.replace("\"", "\"\"");
//		}
//		return result;
//	}

	public static void writeLine(Writer w, List<String> values, char separators) throws IOException {

		boolean first = true;

		//default customQuote is empty

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
		sb.append("\n");
		w.append(sb.toString());


	}
}
