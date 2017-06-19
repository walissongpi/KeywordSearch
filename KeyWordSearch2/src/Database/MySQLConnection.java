/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Walisson
 */
public class MySQLConnection {
private Connection connection;
    //use singleton design patern 
    private static MySQLConnection instance;
    private MySQLConnection(){}
    public static MySQLConnection getInstance(){
        if(instance == null){
            instance = new MySQLConnection();
        
        
        }
        return instance;
    }
 
    public Connection getMySqlConnection() throws Exception {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost/?autoReconnect=true";
	String username = "root";
	String password = "";
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, username, password);
	return conn;
    }
    
    public void disconnect() {
        if(connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }     
    }
}

