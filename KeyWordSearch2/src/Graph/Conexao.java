package Graph;

import Database.MySQLConnection;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import schemacrawler.schema.Database;
import schemacrawler.schema.Schema;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevel;
import schemacrawler.utility.SchemaCrawlerUtility;

public class Conexao {
	static Schema schema;
	static SchemaCrawlerOptions options;
	static Database database;
	Connection connection;
	
        MySQLConnection conn;
	public Conexao(String s){
		options = new SchemaCrawlerOptions();
		options.setSchemaInfoLevel(SchemaInfoLevel.standard());
		try{
                    conn = MySQLConnection.getInstance();
                    connection = conn.getMySqlConnection();
                    database = SchemaCrawlerUtility.getDatabase(connection, options);				
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "erro: "+e.getMessage());
		}					
		schema = database.getSchema(s);	
		
	}

}
