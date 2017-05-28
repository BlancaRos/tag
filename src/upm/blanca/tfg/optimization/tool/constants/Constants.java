package upm.blanca.tfg.optimization.tool.constants;

public class Constants {

	//GENERAL
	public final static String BLANK = "";
	public final static String SEMICOLON = ";";
	public final static String COMMA = ",";
	public final static String LINE_BREAK = "\n";
	public final static String LOW_BAR = "_";
	
	//TABS
	public final static String TAB_DB = "BBDD";
	public final static String TAB_QUERY = "Tipo de consulta";
	public final static String TAB_REPORT = "Consulta e informe";

	//EXPLANATION TABS
	public final static String LABEL_DB = "Elige la Base de Datos a la que realizar la consulta:";
	public final static String LABEL_QUERY = "Selecciona el tipo de consulta a realizar";
	public final static String LABEL_DESCRIPTION_REPORT = "La consulta en formato texto seleccionada es: ";
	public final static String LABEL_SQL_REPORT = "La sentencia SQL seleccionada es: ";
	public final static String LABEL_ADD_BBDD = "No hay Base de Datos almacenadas. Si lo desea, añada una pulsando al botón 'Nueva BBDD'";
	public final static String LABEL_WINDOW_DESCRIPTIONS = "Descripciones almacenadas";
	public final static String LABEL_DESCRIPTIONS = "Seleccione la descripción de la que desee obtener el informe:";
	
	//BBDD NEW
	public final static String ADD_NEW_DB= "Añadir BBDD";
	public final static String INSERT_DB_SERVICE = "1.- Inserte el servicio de la BBDD:";
	public final static String INSERT_DB_NAME = "2.- Inserte un nombre para el servicio:";
	public final static String INSERT_DB_USER = "3.- Introduce el usuario de la BBDD:";
	public final static String INSERT_DB_PASS = "4.- Introduce la contraseña de la BBDD";
	
	//NEW QUERY
	public final static String INSERT_NEW_QUERY = "Añadir consulta";
	public final static String INSERT_NEW_QUERY_SQL = "Introduzca la sentencia sql que desea añadir:";
	
	//POSSIBLE SELECTIONS
	public final static String NEW_TYPE = "Consulta nueva";
	public final static String OLD_TYPE = "Modificar consulta creada";
	public final static String DEFAULT_TYPE = "Seleccione uno...";
	public final static String VEHICLE_DATABASE = "Vehículos";
	public final static String OTHER_DATABASE = "Otro";
	
	//PANEL NAME
	public final static String FIRST_PANEL = "Panel BBDD";
	public final static String SECOND_PANEL = "Panel tipo consulta";
	public final static String THIRD_PANEL = "Panel informe";

	//Explanation of the two types of queries
	public final static String SQL_TEXT = "Diseñe la consulta que desee realizar:";
	public final static String DESCRIPTION_TEXT = "Diseñe la descripcion a consultar:";
	public final static String OPERATION_TYPE = "Seleccione el tipo de consulta que desea ver.";
	
	//Modify query
	public final static String MODIFY_DESCRIPTION_TEXT = "1.- Seleccione la descripción a la que desea añadir una consulta: ";
	public final static String MODIFY_SQL_TEXT = "2.- Seleccione/añada la consulta a realizar: ";


	//BUTTONS
	public final static String SHOW_REPORT ="Ver informe";
	public final static String NEXT_BUTTON = "Siguiente";
	public final static String RESET_BUTTON = "Reset";
	public final static String ADD_BUTTON = "Añadir";
	public final static String ADD_BBDD_BUTTON = "Nueva BBDD";
	public final static String CANCEL_BUTTON = "Cancelar";
	
	
	//MESSAGE DIALOGS
	public final static String CONFIRM_DIALOG = "¿Seguro que desea continuar?";
	public final static String CONFIRM_DIALOG_TITLE = "¡Alerta!";
	public final static String ERROR_DIALOG = "¡Debes seleccionar una opción!";
	public final static String ERROR_DIALOG_TITLE = "¡Error!";
	
	//CSV
	public final static String CSV_PATH = "/Users/admin/Desktop/resultQuery.csv";
	
	//REPORT
	public final static String REPORT_PATH = "/Users/admin/Desktop/TFG/Reports/Informe_";
	public final static String CSV_REPORT_PATH = "/Users/admin/Desktop/TFG/Reports/Csv_";
	//public final static String LOGO_PATH = "/Users/admin/Desktop/TFG/Imagenes/logo_UPM.gif";
	public final static String REPORT_EXTENSION = ".pdf";
	
	//Queries
	public final static String REPORT_VALUES = "select qs.query, db.NombreBBDD, qs.avgTime, qs.rows from QuerySQL qs, BBDD db where db.idBBDD = qs.idBBDD and qs.idQueryDescription = ( select qd.idQueryDescription from QueryDescription qd where Descripcion = '";
	//public final static String REPORT_VALUES = "select qs.query, db.NombreBBDD, qs.avgTime, exec.numFilas from QuerySQL qs, Execution exec, BBDD db where qs.idQuerySQL = exec.idQuerySQL and db.idBBDD = qs.idBBDD and qs.idQueryDescription = ( select qd.idQueryDescription from QueryDescription qd where Descripcion = '";
	public final static String CSV_VALUES = "select csv from QueryDescription where Descripcion = '";
	public final static String INSERT_DESCRIPTION = "INSERT INTO QueryDescription (Descripcion, csv) VALUES(?,?);";
	public final static String INSERT_BBDD = "INSERT INTO BBDD (NombreBBDD, UserBBDD, PassBBDD, ServicioBBDD) VALUES(?,?,?,?);";
	public final static String INSERT_EXECUTION = "INSERT INTO Execution (Fecha, HoraInicio, HoraFin, Tiempo, idQuerySQL, numFilas) VALUES(?,?,?,?,?,?);";
	public final static String INSERT_SQL = "INSERT INTO QuerySQL (query, idBBDD, idQueryDescription, avgTime, rows) VALUES(?,?,?,?,?);";
	public final static String SELECT_DESCRIPTION = "SELECT * FROM QueryDescription where Descripcion = '";
	public final static String SELECT_BBDD = "SELECT * FROM BBDD WHERE ServicioBBDD = '";
	public final static String SELECT_SQL = "SELECT * FROM QuerySQL where query = '";
	public final static String CLOSE_QUERY = "'";
	public final static String CLOSE_QUERY_WITH_SUBQUERY = "')";
	public final static String SELECT_ALL_DESCRIPTION = "SELECT Descripcion FROM QueryDescription";
	public final static String SELECT_ALL_BBDD_NAME = "SELECT NombreBBDD FROM BBDD";
	public final static String SELECT_SQL_FROM_DESCRIPTION = "select query from QuerySQL where idQueryDescription = (select idQueryDescription from QueryDescription where Descripcion = '";
	public final static String SELECT_ALL_DB = "select * from BBDD where NombreBBDD = '";
	public final static String USER_BBDD = "UserBBDD";
	public final static String PASS_BBDD = "PassBBDD";
	public final static String SERVICE_BBDD = "ServicioBBDD";
	
	//JASPERREPORT
	public final static String INFORME = "Informe.jrxml";
	public final static String CSVREPORT = "reportCSV.jrxml";
	
	//APP
	public final static String APP = "java -jar miapp.jar";
	
}