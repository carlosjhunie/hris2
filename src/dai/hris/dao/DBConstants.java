package dai.hris.dao;

public class DBConstants {

	//use username/passwd authentication instead of integratedSecurity auth. Uid/passwd is dai/dai
	//public static final String MS_SQL_DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=dai;integratedSecurity=true;";
	
	public static final String DB_DRIVER_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	
	public static final String MS_SQL_DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=hrisdai;UserName=daimaster;Password=Asdf1234";
	public static final String MS_SQL_DB_URL_BIOMETRIC = "jdbc:sqlserver://localhost:1433;databaseName=hrisbio;UserName=daimaster;Password=Asdf1234";
	
	
}
