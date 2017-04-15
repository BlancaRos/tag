package upm.blanca.tfg.optimization.tool.constants;

public class Constants {

	//GENERAL
	public final static String BLANK = "";
	public final static String SEMICOLON = ";";
	
	//TABS
	public final static String TAB_DB = "BBDD";
	public final static String TAB_QUERY = "Tipo de consulta";
	public final static String TAB_REPORT = "Consulta e informe";

	//EXPLANATION TABS
	public final static String LABEL_DB = "Elige la base de datos a la que realizar la consulta.";
	public final static String LABEL_QUERY = "Selecciona el tipo de consulta a realizar";
	//public final static String LABEL_NATURAL = "¿Qué consulta deseas realizar?";
	//public final static String LABEL_SQL = "¿Qué consulta deseas realizar?";
	public final static String LABEL_REPORT = "La consulta seleccionada es: ";

	//POSSIBLE SELECTIONS
	public final static String NEW_TYPE = "Consulta nueva";
	public final static String OLD_TYPE = "Modificar consulta creada";
	public final static String DEFAULT_TYPE = "Seleccione uno...";
	public final static String VEHICLE_DATABASE = "Vehículos";
	public final static String OTHER_DATABASE = "Otro";
	//public final static String TYPE_SELECT = "Select";
	//public final static String TYPE_DELETE = "Delete";
	//public final static String TYPE_INSERT = "Insert";
	//public final static String TYPE_UPDATE = "Update";
	
	//PANEL NAME
	public final static String FIRST_PANEL = "Panel BBDD";
	public final static String SECOND_PANEL = "Panel tipo consulta";
	public final static String THIRD_PANEL = "Panel informe";

	//Explanation of the two types of queries
	public final static String SQL_TEXT = "Diseñe la consulta que desee realizar.";
	public final static String DESCRIPTION_TEXT = "Diseñe la descripcion a consultar.";
	public final static String OPERATION_TYPE = "Seleccione el tipo de consulta que desea ver.";
	
	//Modify query
	public final static String MODIFY_DESCRIPTION_TEXT = "1) Seleccione la descripción a la que desea añadir una consulta: ";
	public final static String MODIFY_SQL_TEXT = "2) Seleccione/añada la consulta a realizar: ";


	//BUTTONS
	public final static String SENT_QUERY ="Enviar consulta a BBDD";
	public final static String NEXT_BUTTON = "Siguiente";
	
	//MESSAGE DIALOGS
	public final static String CONFIRM_DIALOG = "¿Seguro que desea continuar?";
	public final static String CONFIRM_DIALOG_TITLE = "¡Alerta!";
	public final static String ERROR_DIALOG = "¡Debes seleccionar una opción!";
	public final static String ERROR_DIALOG_TITLE = "¡Error!";

}