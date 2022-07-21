import java.sql.*;
import java.util.ArrayList;

public class ConnectionWithDatabase {
	 
	private String driver = "org.sqlite.JDBC";
	private String url    = "jdbc:sqlite:C:\\cdbase.db"; //for demonstration purposes
	
	private java.sql.Connection sqliteConnection = null;
	
	public ConnectionWithDatabase(String url) throws ClassNotFoundException, SQLException
	{
	    Class.forName(driver); // load sqlite driver
	    this.url = url;
	    sqliteConnection = DriverManager.getConnection(this.url, "", "");
	}
	
	public ArrayList<String> getThisColumn(String columnName, String table) throws SQLException
	{
		ArrayList<String> column = new ArrayList<>();
		
		Statement  statement;

	    statement = sqliteConnection.createStatement () ;

	    String query = " SELECT " + columnName + " FROM " + table;
	    ResultSet rs = statement.executeQuery (query) ;
 
	    while (rs.next())
	    {
	    	column.add(rs.getString(columnName));
	    }

	    rs.close () ;
	    
	    return column;
	}
	
	public void insertARow(String table, ArrayList<String> columns, ArrayList<String> fields) throws SQLException
	{
		/* 	execution of a query like the following
		 *  INSERT 
			INTO product (name, price, description)
			VALUES ("superAcrylic", 8, "εξωτερικού χώρου");
		 */
		
		String stringOfColumns, stringOfFields;
		int stringOfColumnsLength, stringOfFieldsLength;
		
		stringOfColumns = columns.toString();
		System.out.println(stringOfColumns);
		stringOfColumnsLength = columns.size();
		System.out.println(stringOfColumnsLength);
		/*
		stringOfColumns.replace('[', '(');
		stringOfColumns.replace((char) 91, (char) 40);
		stringOfColumns.replace(']', ')');
		stringOfColumns.replace((char) 93, (char) 41);
		stringOfColumns.replace('a', 'e');
		
		System.out.println("a".replace('a', 'e'));
		//stringOfColumns.replaceAll("[", "(");
		stringOfColumns = "a";
		("" + stringOfColumns + "").replace('a','e');
		
		System.out.println(stringOfColumns);
		*/
		stringOfFields = fields.toString();
		System.out.println(stringOfFields);
		
		Statement statement;
		statement = sqliteConnection.createStatement();
		String query = "INSERT "
				+ "INTO " + table + "(" + columns.get(0) + "," + columns.get(1) + "," + columns.get(2) + ") "
						+ "VALUES " + "(" + fields.get(0) + "," + (fields.get(1)) + "," + fields.get(2) +")";
		statement.execute(query);
	}
	
	public void deleteARow(String table, String columnName, String valueOfField) throws SQLException
	{
		/*
		 *  DELETE 
			FROM product
			WHERE product.name='adsf'
		 */
		
		Statement statement;
		statement = sqliteConnection.createStatement();
		
		String query = "DELETE "
				+ " FROM " + table 
				+ " WHERE " + table + "." + columnName + "=" + valueOfField;
		
		statement.execute(query);
	}
	
	public String getInfoOfARow(ArrayList<String> columnsName, String columnNameForRowName, String rowName, String table) throws SQLException
	{
		ArrayList<String> rowInArray = new ArrayList<>();
		String row="";
		
		Statement  statement;

	    statement = sqliteConnection.createStatement () ;
	    //System.out.println(columnNameForRowName);
	    //System.out.println(rowName);

	    String query = " SELECT * FROM " + table + 
	    			   " WHERE " + columnNameForRowName + " = " + rowName;
	    ResultSet rs = statement.executeQuery (query) ;
 
	    while (rs.next())
	    {
	    	for(String column: columnsName)
	    		rowInArray.add(rs.getString(column));
	    }

	    rs.close () ;
	    
    	row = String.join(" ", rowInArray);
	    
	    return row;
	}
	
	public double getTheDoubleValueOfAFieldOfARow(String field, String columnNameForRowName, String rowName, String table) throws SQLException
	{
		
		Statement  statement;

	    statement = sqliteConnection.createStatement () ;

	    String query = " SELECT " + field + " FROM " + table +
	    			   " WHERE " + columnNameForRowName + "=" + rowName;
	    ResultSet rs = statement.executeQuery (query) ;
	    
	    double value = Double.parseDouble(rs.getString(field));

	    rs.close () ;
	    
	    return value;
	}
	
	public String getTheStringValueOfAFieldOfARow(String field, String columnNameForRowName, String rowName, String table) throws SQLException
	{
		
		Statement  statement;

	    statement = sqliteConnection.createStatement () ;

	    String query = " SELECT " + field + " FROM " + table +
	    			   " WHERE " + columnNameForRowName + "=" + rowName;
	    ResultSet rs = statement.executeQuery (query) ;
	    
	    String value = rs.getString(field);

	    rs.close () ;
	    
	    return value;
	}
	
	public void CloseConnection() throws SQLException
	{
		sqliteConnection.close();
	}
}
