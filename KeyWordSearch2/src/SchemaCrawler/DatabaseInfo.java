/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchemaCrawler;

import Database.MySQLConnection;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import schemacrawler.schema.Column;
import schemacrawler.schema.Database;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevel;
import schemacrawler.utility.SchemaCrawlerUtility;

/**
 *
 * @author Walisson
 */
public class DatabaseInfo {
    static Connection connection;
    static SchemaCrawlerOptions options;
    static Database database;
    static Schema schema; 
    
    String databaseName;
    
    public DatabaseInfo(String databaseName){
        this.databaseName=databaseName;
        options = new SchemaCrawlerOptions();
	options.setSchemaInfoLevel(SchemaInfoLevel.standard());
        try {
            connection = MySQLConnection.getInstance().getMySqlConnection();
            database = SchemaCrawlerUtility.getDatabase(connection, options);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
	schema = database.getSchema(databaseName);
    }

    public ArrayList<String> getTableNames(){
        ArrayList<String> tableList = new ArrayList<String>();
        Table [] tables = schema.getTables();
        for(Table table: tables){
            tableList.add(table.getName());
        }
        return tableList;
    }
    
    public ArrayList<String> getAttributes(String tableName){
        ArrayList<String> attributeList = new ArrayList<String>();
         Table table = schema.getTable(tableName);
           for(Column c: table.getColumns()){
		attributeList.add(tableName+"."+c.getName());
            }
        return attributeList;     
    }
    
    public String getAttributeType(String tableName, String attributeName){
        Table table = schema.getTable(tableName);
        return table.getColumn(attributeName).getType().getTypeClassName();    
    }
    
    public ArrayList<String> getAttributesType(String tableName){
       Table table = schema.getTable(tableName);
        ArrayList<String> listTypes = new ArrayList<String>();
           for(Column c: table.getColumns()){
             listTypes.add(c.getType().getTypeClassName());
            }
        return  listTypes;
    }
    
    public ArrayList<String> getDatabaseAttributes(){
        ArrayList<String> databaseAttributes = new ArrayList<String>();       
        Table [] tables = schema.getTables();
        for(Table table: tables){
            databaseAttributes.add(table.getName());
            for(String attribute: getAttributes(table.getName())){
                databaseAttributes.add(attribute.replace("`",""));
            }
        }
        return databaseAttributes;
    }

    public ArrayList<String> getDatabaseOnlyAttributes(){//atributos do banco sem o nome da tabela no atributo
        ArrayList<String> databaseAttributes = new ArrayList<String>();       
        Table [] tables = schema.getTables();
        for(Table table: tables){
            databaseAttributes.add(table.getName());
            for(String attribute: getAttributes(table.getName())){             
                databaseAttributes.add(attribute.replace("`","").replace(table.getName()+".",""));
            }
        }
        return databaseAttributes;
    }
    
    /*public static void main(String[]args){
        DatabaseInfo d = new DatabaseInfo("imdbpart");
        for(String s: d.getDatabaseAttributes()){
            System.out.println(s);
        }
    }*/
}
