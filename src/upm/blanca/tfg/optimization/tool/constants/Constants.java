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
	public final static String TAB_REPORT = "Consulta e informes";

	//EXPLICACION TABS
	public final static String LABEL_DB = "Selecciona la BBDD a la que realizar la consulta:";
	public final static String LABEL_QUERY = "Selecciona el tipo de consulta a realizar";
	public final static String LABEL_DESCRIPTION_REPORT = "La descripción seleccionada es: ";
	public final static String LABEL_SQL_REPORT = "La sentencia SQL seleccionada es: ";
	public final static String LABEL_ADD_BBDD = "No se ha podido recuperar la Base de Datos.";
	public final static String LABEL_WINDOW_DESCRIPTIONS = "Descripciones almacenadas";
	public final static String LABEL_DESCRIPTIONS = "Seleccione la descripción mediante la cual obtendrá el informe:";
	
	//NUEVA BBDD 
	public final static String ADD_NEW_DB= "Añadir BBDD";
	public final static String INSERT_DB_SERVICE = "1.- Inserte el servicio de la BBDD:";
	public final static String INSERT_DB_NAME = "2.- Inserte un nombre para el servicio:";
	public final static String INSERT_DB_USER = "3.- Introduce el usuario de la BBDD:";
	public final static String INSERT_DB_PASS = "4.- Introduce la contraseña de la BBDD";
	
	//NUEVA QUERY
	public final static String INSERT_NEW_QUERY = "Añadir consulta";
	public final static String INSERT_NEW_QUERY_SQL = "Introduzca la sentencia SQL:";
	
	//DESPLEGABLE
	public final static String NEW_TYPE = "Consulta nueva";
	public final static String OLD_TYPE = "Modificar consulta creada";
	public final static String DEFAULT_TYPE = "Seleccione uno...";
	
	//NOMBRE PANEL 
	public final static String FIRST_PANEL = "Panel BBDD";
	public final static String SECOND_PANEL = "Panel tipo consulta";
	public final static String THIRD_PANEL = "Panel informe";

	//Explicacion de los dos tipos de queries
	public final static String SQL_TEXT = "Diseñe la consulta que desee realizar:";
	public final static String DESCRIPTION_TEXT = "Diseñe la descripcion a consultar:";
	
	//Modificar query
	public final static String MODIFY_DESCRIPTION_TEXT = "1.- Descripción seleccionada: ";
	public final static String MODIFY_SQL_TEXT = "2.- Seleccione o añada la sentencia SQL a realizar: ";

	//Botones
	public final static String SHOW_REPORT ="Ver informe";
	public final static String SHOW_CSV_REPORT ="Ver resultado";
	public final static String NEXT_BUTTON = "Siguiente";
	public final static String RESET_BUTTON = "Reset";
	public final static String ADD_BUTTON = "Añadir";
	public final static String ADD_BBDD_BUTTON = "Nueva BBDD";
	public final static String CANCEL_BUTTON = "Cancelar";
	
	//MESSAGE DIALOGS
	public final static String CONFIRM_DIALOG = "¿Seguro que desea continuar?";
	public final static String SENT_QUERY_DIALOG = "¿Desea realizar la consulta?";
	public final static String CONFIRM_DIALOG_TITLE = "¡Alerta!";
	public final static String ERROR_DIALOG = "¡Debes seleccionar una opción!";
	public final static String ERROR_DIALOG_TITLE = "¡Error!";
	public final static String ERROR_SQL = "Error en la sentencia SQL";
	public final static String ERROR_SQL_TITLE = "ERROR SQL";
	public final static String ERROR_BBDD = "No es posible conectarse a la BBDD Externa";
	public final static String ERROR_QUERY = "La sentencia SQL no se corresponde con la descripción seleccionada";
	public final static String ERROR_BBDD_TITLE = "ERROR BBDD";
	public final static String EMPTY_QUERY = "No se ha obtenido nada para la consulta seleccionada";
	public final static String EMPTY_QUERY_TITLE = "Resultado de consulta vacío";
	
	//CSV
	public final static String CSV_PATH = "/Users/admin/Desktop/resultQuery.csv";
	
	//Informe
	public final static String REPORT_PATH = "/Users/admin/Desktop/TFG/Reports/Informe_";
	public final static String CSV_REPORT_PATH = "/Users/admin/Desktop/TFG/Reports/Resultado_";
	public final static String REPORT_EXTENSION = ".pdf";
	
	//Queries utiles
	public final static String REPORT_VALUES = "select qs.query, db.DBName, qs.avgTime, qs.rows from QuerySQL qs, BBDD db where db.idDB = qs.idDB and qs.idQueryDescription = ( select qd.idQueryDescription from QueryDescription qd where description = '";
	public final static String CSV_VALUES = "select csv from QueryDescription where description = '";
	public final static String INSERT_DESCRIPTION = "INSERT INTO QueryDescription (idDB, description, csv) VALUES(?,?,?);";
	public final static String INSERT_BBDD = "INSERT INTO BBDD (DBName, DBUser, DBPass, DBService) VALUES(?,?,?,?);";
	public final static String INSERT_EXECUTION = "INSERT INTO Execution (executionDate, startTime, endTime, avgTime, idQuerySQL, numRows) VALUES(?,?,?,?,?,?);";
	public final static String INSERT_SQL = "INSERT INTO QuerySQL (query, idDB, idQueryDescription, avgTime, rows) VALUES(?,?,?,?,?);";
	public final static String SELECT_DESCRIPTION = "SELECT * FROM QueryDescription where description = '";
	public final static String SELECT_BBDD = "SELECT * FROM BBDD WHERE DBService = '";
	public final static String SELECT_SQL = "SELECT * FROM QuerySQL where query = '";
	public final static String CLOSE_QUERY = "'";
	public final static String CLOSE_QUERY_WITH_SUBQUERY = "')";
	public final static String SELECT_ALL_DESCRIPTION = "SELECT description FROM QueryDescription where idDB = '";
	public final static String SELECT_ALL_BBDD_NAME = "SELECT DBName FROM BBDD";
	public final static String SELECT_SQL_FROM_DESCRIPTION = "select query from QuerySQL where idQueryDescription = (select idQueryDescription from QueryDescription where description = '";
	public final static String SELECT_ALL_DB = "select * from BBDD where DBName = '";
	public final static String USER_BBDD = "DBUser";
	public final static String PASS_BBDD = "DBPass";
	public final static String SERVICE_BBDD = "DBService";
	public static final String IDDB = "idDB";
	public final static String CHECK_ROWS = "SELECT count(*) FROM QuerySQL WHERE idQueryDescription = (SELECT idQueryDescription FROM QueryDescription WHERE description = '";
	
	//JASPERREPORT
	public final static String INFORME = "Informe.jrxml";
	public final static String CSVREPORT = "reportCSV.jrxml";
	
	//APP
	public final static String APP = "java -jar miapp.jar";
	
}