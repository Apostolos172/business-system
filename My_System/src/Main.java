import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This is the basic class in order to run the program
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		new Main_GUI();
		
	    //ConnectionWithDatabase conn = new ConnectionWithDatabase("jdbc:sqlite:C:\\Users\\user\\Desktop\\ΠΑ.ΜΑΚ\\ΕΦΑΡΜΟΣΜΕΝΗ\\3ο εξάμηνο\\βάσεις δεδομένων 1\\εργαστήριο\\Lab_13\\Lab_12\\cdbase.db");
	    //ConnectionWithDatabase conn = new ConnectionWithDatabase("jdbc:sqlite:C:\\Users\\user\\Desktop\\-\\applications\\My_System\\shop.db");
		String path = "C:\\backups\\dbs\\shop.db";
		path = "C:\\databases\\shop.db";
		ConnectionWithDatabase conn = new ConnectionWithDatabase("jdbc:sqlite:" + path);
/*
	    conn.executeStatement("SELECT * FROM cd");
	    System.out.println("-----------------------------");

	    
	    ArrayList<String> column = new ArrayList<>(conn.getThisColumn("year", "cd"));
	    for(String s: column)
	    {
	    	System.out.println(s);
	    }
	    System.out.println("-----------------------------");
	    
	    ArrayList<String> columns = new ArrayList<>();
	    columns.add("cid");
	    columns.add("ctitle");
	    columns.add("year");
	    String row = conn.getInfoOfARow(columns, "ctitle", "'Aftershock'", "cd");
	    System.out.println(row);
	    System.out.println("-----------------------------");

	    conn.CloseConnection();
*/ 
	   
	    ArrayList<String> columns,fields;
	    columns = new ArrayList<>();
	    columns.add("name");
	    columns.add("price");
	    columns.add("description");
	    fields = new ArrayList<>();
	    fields.add("'decorPlast'");
	    fields.add("2");
	    fields.add("'εσωτερικού χώρου'");
	    
	    //conn.insertARow("product", columns, fields);
	    //A PRIMARY KEY constraint failed (UNIQUE constraint failed: product.name)r
	    
		System.out.println(conn.getTheDoubleValueOfAFieldOfARow("price", "name", "'superPlastic'", "product"));
		System.out.println("-----------------------------");
	    for(String name: conn.getThisColumn("name", "product"))
	    	System.out.println(name);
		System.out.println("-----------------------------");
	    StockList list = new StockList();
	    //list.getInfoOfTheProductWithThisName("superPlastic");
	    for(String name: list.getTheNamesOfProducts())
	    	System.out.println(name);
		System.out.println("-----------------------------");
		System.out.println(list.getInfoOfTheProductWithThisName("'superPlastic'"));
		System.out.println("-----------------------------");

	    for(Product product: list.getProducts())
	    	System.out.println(product.getData3());
	}

}