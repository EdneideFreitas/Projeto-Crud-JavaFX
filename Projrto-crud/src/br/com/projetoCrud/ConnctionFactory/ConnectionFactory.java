package br.com.projetoCrud.ConnctionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class ConnectionFactory {

	
	public   Connection conexaoFactory() throws SQLException{	
		String URL="jdbc:mysql://localhost/crud";
		return DriverManager.getConnection(URL,"root","");
	}
}
